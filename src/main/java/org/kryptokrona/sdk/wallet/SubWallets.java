package org.kryptokrona.sdk.wallet;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.exception.wallet.WalletSubNotFoundException;
import org.kryptokrona.sdk.model.util.TxInputAndOwner;
import org.kryptokrona.sdk.model.util.UnconfirmedInput;
import org.kryptokrona.sdk.transaction.Transaction;
import org.kryptokrona.sdk.transaction.TransactionInput;
import org.kryptokrona.sdk.util.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SubWallets.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class SubWallets {

	/**
	 * Whether the wallet is a view only wallet (cannot send transactions,
	 * only can view).
	 */
	private boolean isViewWallet;

	/**
	 * The public spend keys this wallet contains. Used for verifying if a
	 * transaction is ours.
	 */
	private List<String> publicSpendKeys;

	/**
	 * Mapping of public spend key to sub wallet.
	 */
	private Map<String, SubWallet> subWallets;

	/**
	 * Our transactions.
	 */
	private List<Transaction> transactions;

	/**
	 * Transactions we sent, but haven't been confirmed yet.
	 */
	private List<Transaction> lockedTransactions;

	/**
	 * The shared private view key.
	 */
	private String privateViewKey;

	/**
	 * A mapping of transaction hashes, to transaction private keys.
	 */
	private Map<String, String> transactionPrivateKeys;

	/**
	 * A mapping of key images to the sub wallet public spend key that owns them.
	 */
	private Map<String, String> keyImageOwners;

	private static final Logger logger = LoggerFactory.getLogger(SubWallets.class);

	public SubWallets(boolean isViewWallet, List<String> publicSpendKeys, String privateViewKey) {
		this.isViewWallet = isViewWallet;
		this.publicSpendKeys = publicSpendKeys;
		this.privateViewKey = privateViewKey;
	}

	public static Observable<List<SubWallets>> init() {
		return Observable.empty();
	}

	public void initKeyImageMap() {

	}

	public void pruneSpentInputs(long pruneHeight) {

	}

	public void reset(long scanHeight, long scanTimestamp) {

	}

	public void rewind(long scanHeight) {

	}

	public String getPrivateSpendKey(String publicSpendKey) {
		return "";
	}

	public SubWallet getPrimarySubWallet() {
		return null;
	}

	public String getPrimaryAddress() {
		return null;
	}

	public String getPrimaryPrivateSpendKey() {
		return null;
	}

	public List<String> getLockedTransactionHashes() {
		return null;
	}

	/**
	 * Add this transaction to the container. If the transaction was previously
	 * sent by us, remove it from the locked container
	 *
	 * @param transaction The transaction to be added
	 */
	public void addTransaction(Transaction transaction) {
		logger.trace("Transaction " + transaction.getHash());

		/* Remove this transaction from the locked data structure, if we had
           added it previously as an outgoing tx */
		lockedTransactions.removeIf(t -> t.getHash().equals(transaction.getHash()));

		// check if transaction is already in list of transactions
		var transactionInTransaction = transactions.stream()
				.filter(t -> t.getHash().equals(transaction.getHash()))
				.findAny();

		if (transactionInTransaction.isPresent()) {
			logger.debug("Already seen transaction " + transaction.getHash() + ", ignoring.");
		}

		transactions.add(transaction);
	}

	/**
	 * Adds a transaction we sent to the locked transactions container
	 *
	 * @param transaction The transaction to be added
	 */
	public void addUnconfirmedTransaction(Transaction transaction) {
		logger.trace("Unconfirmed transaction " + transaction.getHash());

		var transactionInLockedTransaction = lockedTransactions.stream()
				.filter(t -> t.getHash().equals(transaction.getHash()))
				.findAny();

		if (transactionInLockedTransaction.isPresent()) {
			logger.debug("Already seen unconfirmed transaction " + transaction.getHash() + ", ignoring.");
		}

		lockedTransactions.add(transaction);
	}

	/**
	 * Store the transaction input in the corresponding sub wallet.
	 *
	 * @param publicSpendKey The public spend key of the sub wallet to add this input to
	 * @param transactionInput The transaction input to store
	 */
	public void storeTransactionInput(String publicSpendKey, TransactionInput transactionInput) throws WalletSubNotFoundException {
		var subWallet = subWallets.values()
				.stream()
				.filter(sw -> sw.getPublicSpendKey().equals(publicSpendKey))
				.findFirst()
				.orElse(null);

		if (subWallet == null) {
			throw new WalletSubNotFoundException();
		}

		logger.trace("Input key image " + transactionInput.getKeyImage());

		if (!isViewWallet) {
			keyImageOwners.put(transactionInput.getKeyImage(), publicSpendKey);
		}

		subWallet.storeTransactionInput(transactionInput, isViewWallet);
	}

	/**
	 * Marks an input as spent by us, no longer part of balance or available
	 * for spending. Input is identified by keyImage (unique).
	 *
	 * @param publicSpendKey The public spend key of the sub wallet to mark the corresponding input spent in
	 * @param keyImage The key image to use
	 * @param spendHeight The height the input was spent at
	 */
	public void markInputAsSpent(String publicSpendKey, String keyImage, long spendHeight) throws WalletSubNotFoundException {
		var subWallet = subWallets.values()
				.stream()
				.filter(sw -> sw.getPublicSpendKey().equals(publicSpendKey))
				.findFirst()
				.orElse(null);

		if (subWallet == null) {
			throw new WalletSubNotFoundException();
		}

		subWallet.markInputAsSpent(keyImage, spendHeight);
	}

	public void markInputAsLocked(String publicSpendKey, String keyImage, String transactionHash) throws WalletSubNotFoundException {
		var subWallet = subWallets.values()
				.stream()
				.filter(sw -> sw.getPublicSpendKey().equals(publicSpendKey))
				.findFirst()
				.orElse(null);

		if (subWallet == null) {
			throw new WalletSubNotFoundException();
		}

		subWallet.markInputAsLocked(keyImage, transactionHash);
	}

	/**
	 * Remove a transaction that we sent by didn't get included in a block and
	 * returned to us. Removes the correspoding inputs, too.
	 *
	 * @param transactionHash The transaction hash of the transaction to remove
	 */
	public void removeCancelledTransaction(String transactionHash) {
		// remove the transaction if it was locked
		lockedTransactions.removeIf(t -> t.getHash().equals(transactionHash));

		// remove the corresponding inputs
		for (var subWallet : subWallets.values()) {
			subWallet.removeCancelledTransaction(transactionHash);
		}
	}

	/**
	 * Remove transactions which occured in a forked block. If they got added
	 * in another block, we'll add them back again then.
	 *
	 * @param forkHeight Remove transactions on a specific fork block
	 */
	public void removeForkedTransactions(long forkHeight) {
		transactions.removeIf(t -> t.getBlockHeight() >= forkHeight);

		var keyImagesToRemove = new ArrayList<String>();

		for (var subWallet : subWallets.values()) {
			keyImagesToRemove.addAll(subWallet.removeForkedTransactions(forkHeight));
		}

		if (!isViewWallet) {
			for (var keyImage : keyImagesToRemove) {
				keyImageOwners.keySet().removeIf(ki -> ki.equals(keyImage));
			}
		}
	}

	/**
	 * Convert a timestamp to a block height. Block heights are more dependable
	 * than timestamps, which sometimes get treated a little funkily by the
	 * daemon.
	 *
	 * @param timestamp The timestamp to convert
	 * @param height The block height to use
	 */
	public void convertSyncTimestampToHeight(long timestamp, long height) {
		for (var subWallet : subWallets.values()) {
			subWallet.convertSyncTimestampToHeight(timestamp, height);
		}
	}

	public boolean haveSpendableInput(TransactionInput transactionInput, long height) {
		for (var subWallet : subWallets.values()) {
			if (subWallet.haveSpendableInput(transactionInput, height)) {
				return true;
			}
		}

		return false;
	}

	public Map<Boolean, String> getKeyImageOwner(String keyImage) {
		return null;
	}

	public Observable<Map<String, String>> getTxInputKeyImage(String publicSpendKey, String derivation, long outputIndex) {
		return Observable.empty();
	}

	/**
	 * Returns the summed balance of the given sub wallet addresses. If none are given,
	 * take from all.
	 *
	 * @param currentHeight The current height to use
	 * @param subWalletsToTakeFrom Which sub wallets to take from
	 * @return Observable with a map
	 */
	public Observable<Map<Double, Double>> getBalance(long currentHeight, List<String> subWalletsToTakeFrom) {
		List<String> publicSpendKeys = new ArrayList<>();

		if (subWalletsToTakeFrom.size() == 0) {
			publicSpendKeys = this.publicSpendKeys;
		} else {
			for (var address : subWalletsToTakeFrom) {
				CryptoUtils.addressToKeys(address).subscribe(str -> {
					this.publicSpendKeys.add(str.keySet().toString());
				});
			}
		}

		var unlockedBalance = 0.0;
		var lockedBalance = 0.0;

		/* For faster lookups in case we have a ton of transactions or
           sub wallets to take from */
		// var lookupMap = new HashMap(publicSpendKeys)

		for (var transaction : transactions) {
			var unlocked = CryptoUtils.isInputUnlocked(transaction.getUnlockTime(), currentHeight);
			//TODO: finish this implementation

		}

		return Observable.empty();
	}

	public List<String> getAddresses() {
		return null;
	}

	public Observable<List<TxInputAndOwner>> getSpendableTransactionInputs(List<String> subWalletsToTakeFrom, long currentHeight) {
		return Observable.empty();
	}

	public Observable<Map<List<TxInputAndOwner>, Long>> getFusionTransactionInputs(List<String> subWalletsToTakeFrom, long mixin, long currentHeight) {
		return Observable.empty();
	}

	public void storeTxPrivateKey(String txPrivateKey, String txHash) {

	}

	public void storeUnconfirmedIncomingInput(UnconfirmedInput unconfirmedInput, String publicSpendKey) {

	}

	public Observable<List<Transaction>> getTransactions(String address, boolean includeFusions) {
		return Observable.empty();
	}

	public Observable<Long> getNumTransactions(String address, boolean includeFusions) {
		return Observable.empty();
	}

	public Observable<List<Transaction>> getUnconfirmedTransactions(String address, boolean includeFusions) {
		return Observable.empty();
	}

	public Observable<Long> getNumUnconfirmedTransactions(String address, boolean includeFusions) {
		return Observable.empty();
	}

	public Observable<Void> addSubWallet(long scanHeight) {
		return Observable.empty();
	}

	public Observable<Void> importSubWallet(String privateSpendKey, long scanHeight) {
		return Observable.empty();
	}

	public Observable<Void> importViewSubWallet(String publicSpendKey, long scanHeight) {
		return Observable.empty();
	}

	public Observable<Boolean> deleteSubWallet(String address) {
		return Observable.empty();
	}

	public long getWalletCount() {
		return 0;
	}

	private void deleteAddressTransactions(List<Transaction> transactions, String publicSpendKey) {

	}

	private Observable<List<Transaction>> filterTransactions(List<Transaction> transactions, String address, boolean includeFusions) {
		return Observable.empty();
	}
}
