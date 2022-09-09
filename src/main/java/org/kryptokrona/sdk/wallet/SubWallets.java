package org.kryptokrona.sdk.wallet;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.model.util.TxInputAndOwner;
import org.kryptokrona.sdk.model.util.UnconfirmedInput;
import org.kryptokrona.sdk.transaction.Transaction;
import org.kryptokrona.sdk.transaction.TransactionInput;

import java.util.List;
import java.util.Map;

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
	private Map<String, SubWallets> subWallets;

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

	public void addTransaction(Transaction transaction) {

	}

	public void addUnconfirmedTransaction(Transaction transaction) {

	}

	/**
	 * Store the transaction input in the corresponding sub wallet.
	 *
	 * @param publicSpendKey The public spend key of the sub wallet to add this input to
	 * @param transactionInput The transaction input to store
	 */
	public void storeTransactionInput(String publicSpendKey, TransactionInput transactionInput) {

	}

	/**
	 * Marks an input as spent by us, no longer part of balance or available
	 * for spending. Input is identified by keyImage (unique).
	 *
	 * @param publicSpendKey The public spend key of the sub wallet to mark the corresponding input spent in
	 * @param keyImage The key image to use
	 * @param spendHeight The height the input was spent at
	 */
	public void markInputAsSpent(String publicSpendKey, String keyImage, long spendHeight) {

	}

	public void markInputAsLocked(String publicSpendKey, String keyImage, String transactionHash) {

	}

	public void removeCancelledTransaction(String transactionHash) {

	}

	public void removeForkedTransactions(long forkHeight) {

	}

	public void convertSyncTimestampToHeight(long timestamp, long height) {

	}

	public boolean haveSpendableInput(TransactionInput transactionInput, long height) {
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
