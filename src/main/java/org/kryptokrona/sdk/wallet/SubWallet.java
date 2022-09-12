package org.kryptokrona.sdk.wallet;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.Setter;
import org.kryptokrona.sdk.model.util.TxInputAndOwner;
import org.kryptokrona.sdk.model.util.UnconfirmedInput;
import org.kryptokrona.sdk.transaction.TransactionInput;

import java.util.List;
import java.util.Map;

/**
 * SubWallet.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class SubWallet {

	/**
	 * A vector of the stored transaction input data, to be used for
	 * sending transactions later.
	 */
	private List<TransactionInput> unspentInputs;

	/**
	 * Inputs which have been used in a transaction, and are waiting to
	 * either be put into a block, or return to our wallet.
	 */
	private List<TransactionInput> lockedInputs;

	/**
	 * Inputs which have been spent in a transaction.
	 */
	private List<TransactionInput> spentInputs;

	/**
	 * Inputs which have come in from a transaction we sent - either from
	 * change or from sending to ourself - we use this to display unlocked
	 * balance correctly.
	 */
	private List<UnconfirmedInput> unconfirmedIncomingAmounts;

	/**
	 * This subwallet's public spend key.
	 */
	private String publicSpendKey;

	/**
	 * The subwallet's private spend key (undefined if view wallet).
	 */
	private String privateSpendKey;

	/**
	 * The timestamp to begin syncing the wallet at
	 * (usually creation time or zero).
	 */
	private long syncStartTimestamp;

	/**
	 * The height to begin syncing the wallet at.
	 */
	private long syncStartHeight;

	/**
	 * This subwallet's public address.
	 */
	private String address;

	/**
	 * The wallet has one 'main' address which we will use by default
	 * when treating it as a single user wallet.
	 */
	private boolean isPrimaryAddress;

	public SubWallet(
			List<TransactionInput> unspentInputs, List<TransactionInput> lockedInputs,
			List<TransactionInput> spentInputs, List<UnconfirmedInput> unconfirmedIncomingAmounts,
			String publicSpendKey, String privateSpendKey, long syncStartTimestamp,
			long syncStartHeight, String address, boolean isPrimaryAddress
	) {
		this.unspentInputs = unspentInputs;
		this.lockedInputs = lockedInputs;
		this.spentInputs = spentInputs;
		this.unconfirmedIncomingAmounts = unconfirmedIncomingAmounts;
		this.publicSpendKey = publicSpendKey;
		this.privateSpendKey = privateSpendKey;
		this.syncStartTimestamp = syncStartTimestamp;
		this.syncStartHeight = syncStartHeight;
		this.address = address;
		this.isPrimaryAddress = isPrimaryAddress;
	}

	public void pruneSpentInputs(long pruneHeight) {

	}

	public void reset(long scanHeight, long scanTimestamp) {

	}

	public void markInputAsSpent(String keyImage, long spendHeight) {

	}

	public void markInputAsLocked(String keyImage, String transactionHash) {

	}

	public void removeCancelledTransaction(String transactionHash) {

	}

	public List<String> removeForkedTransactions(long forkHeight) {
		return null;
	}

	public void convertSyncTimestampToHeight(long startTimestamp, long startHeight) {

	}

	public List<String> getKeyImages() {
		return null;
	}

	public Observable<Map<String, String>> getTxInputKeyImage(String derivation, long outputIndex) {
		return Observable.empty();
	}

	public Map<Double, Double> getBalance(long currentHeight) {
		return null;
	}

	public long getUnconfirmedChange() {
		return 0;
	}

	public boolean haveSpendableInput(TransactionInput transactionInput, long currentHeight) {
		return false;
	}

	public List<TxInputAndOwner> getSpendableInputs(long currentHeight) {
		return null;
	}

	public void storeTransactionInput(TransactionInput transactionInput, boolean visViewWallet) {

	}

	public void storeUnconfirmedIncomingInput(UnconfirmedInput unconfirmedInput) {

	}
}
