package org.kryptokrona.sdk.service;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.block.Block;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.crypto.KeyPair;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.transaction.TransactionData;
import org.kryptokrona.sdk.transaction.TransactionInputImpl;
import org.kryptokrona.sdk.transaction.TransactionRaw;
import org.kryptokrona.sdk.transaction.TransactionRawCoinbase;
import org.kryptokrona.sdk.wallet.SubWallets;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WalletSynchronizerService.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class WalletSynchronizerService {

	private DaemonImpl daemon;
	private long startTimestamp;
	private long startHeight;
	private String privateViewKey;
	private SynchronizationStatus synchronizationStatus;
	private List<SubWallets> subWallets;
	private boolean fetchingBlocks;
	private List<Block> storedBlocks;
	private Map<String, Long> cancelledTransactionsFailCount;
	private Instant lastDownloadedBlocks;
	private Config config;

	public WalletSynchronizerService(
			DaemonImpl daemon,
			List<SubWallets> subWallets,
			long startTimestamp,
			long startHeight,
			String privateViewKey,
			Config config
	) {
		this.daemon = daemon;
		this.subWallets = subWallets;
		this.startTimestamp = startTimestamp;
		this.startHeight = startHeight;
		this.privateViewKey = privateViewKey;
		this.lastDownloadedBlocks = Instant.now();
		this.config = config;
	}

	public void initAfterLoad(
			List<SubWallets> subWallets,
			DaemonImpl daemon,
			Config config
	) {
		this.subWallets = subWallets;
		this.daemon = daemon;
		this.config = config;
		this.storedBlocks = new ArrayList<>();
		this.cancelledTransactionsFailCount = new HashMap<>();
		this.lastDownloadedBlocks = Instant.now();
	}

	public TransactionData processBlock(Block block, Map<String, TransactionInputImpl> ourInputs) {
		var txData = new TransactionData();

		// TODO: implement if statement and logic inside when crypto library is available in java

		txData.setInputsToAdd(ourInputs);

		return txData;
	}

	/**
	 * Process transaction outputs of the given block. No external dependencies,
	 * lets us easily swap out with a C++ replacement for SPEEEED
	 *
	 * @param block The block to use
	 * @param privateViewKey The private view key
	 * @param allSpendKeys List of spend keys
	 * @param isViewWallet If its a view wallet
	 * @param processCoinbaseTransactions If we should process coinbase transactions
	 * @return Returns a map of transaction outputs
	 */
	public Observable<Map<String, List<TransactionInputImpl>>> processBlockOutputs(
			Block block,
			String privateViewKey,
			List<KeyPair> allSpendKeys,
			boolean isViewWallet,
			boolean processCoinbaseTransactions
	) {
		var inputs = new HashMap<String, List<TransactionInputImpl>>();

		if (processCoinbaseTransactions && (block.getTransactionRawCoinbase() != null)) {
			// inputs.values().addAll(processTransactionOutputs(block.getTransactionRawCoinbase(), block.getBlockHeight()))
		}

		for (var tx : block.getTransactions()) {
			processTransactionOutputs(tx, block.getBlockHeight()).subscribe(list -> {
				inputs.values().addAll(list.values());
			});
		}

		return Observable.just(inputs);
	}

	/**
	 * Get the height of the sync process.
	 *
	 * @return long : last known block height
	 */
	public long getHeight() {
		return this.synchronizationStatus.getLastKnownBlockHeight();
	}

	public Observable<Void> reset(long scanHeight, long scanTimestamp) {
		return null;
	}

	public Observable<Void> rewind(long scanHeight) {
		return null;
	}

	public Observable<List<String>> findCancelledTransactions(List<String> transactionHashes) {
		return null;
	}

	/**
	 * Retrieve blockCount blocks from the internal store. Does not remove
	 * them.
	 *
	 * @return Observable
	 */
	public Observable<Map<List<Block>, Boolean>> fetchBlocks() {
		return null;
	}

	public void dropBlock(long blockHeight, String blockHash) {

	}

	public List<String> getBlockCheckpoints() {
		return null;
	}

	public List<String> getRecentBlockHashes() {
		return null;
	}

	public List<String> getStoredBlockCheckpoints() {
		return null;
	}

	private boolean shouldFetchMoreBlocks() {
		return false;
	}

	public List<String> getWalletSyncDataHashes() {
		return null;
	}

	private Observable<Map<Boolean, Boolean>> downloadBlocks() {
		return null;
	}

	private Observable<Map<String, List<TransactionInputImpl>>> processTransactionOutputs(
			TransactionRawCoinbase rawTx,
			long blockHeight
	) {
		return null;
	}

	private Observable<List<Map<String, List<TransactionInputImpl>>>> processCoinbaseTransaction(
			Block block,
			List<Map<String, TransactionInputImpl>> ourInputs
	) {
		return null;
	}

	//TODO should return another type
	private Observable<Void> processTransaction(
			Block block,
			List<Map<String, TransactionInputImpl>> ourInputs,
			TransactionRaw rawTx
	) {
		return null;
	}
}
