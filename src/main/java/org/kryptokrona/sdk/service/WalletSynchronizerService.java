// Copyright (c) 2022-2022, The Kryptokrona Project
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.sdk.service;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.block.Block;
import org.kryptokrona.sdk.block.TopBlock;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.crypto.KeyPair;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.model.http.WalletSyncData;
import org.kryptokrona.sdk.transaction.TransactionData;
import org.kryptokrona.sdk.transaction.TransactionInputImpl;
import org.kryptokrona.sdk.transaction.TransactionRaw;
import org.kryptokrona.sdk.transaction.TransactionRawCoinbase;
import org.kryptokrona.sdk.wallet.SubWallets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kryptokrona.sdk.config.Config.BLOCKS_PER_TICK;
import static org.kryptokrona.sdk.config.Config.MAX_LAST_FETCHED_BLOCK_INTERVAL;

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

	private static final Logger logger = LoggerFactory.getLogger(WalletSynchronizerService.class);

	public WalletSynchronizerService(
		DaemonImpl daemon,
		List<SubWallets> subWallets,
		long startTimestamp,
		long startHeight,
		String privateViewKey
	) {
		this.daemon = daemon;
		this.subWallets = subWallets;
		this.startTimestamp = startTimestamp;
		this.startHeight = startHeight;
		this.privateViewKey = privateViewKey;
		this.synchronizationStatus = new SynchronizationStatus();
		this.lastDownloadedBlocks = Instant.now();
	}

	public void initAfterLoad(
		List<SubWallets> subWallets,
		DaemonImpl daemon
	) {
		this.subWallets = subWallets;
		this.daemon = daemon;
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
	 * @param block                       The block to use
	 * @param privateViewKey              The private view key
	 * @param allSpendKeys                List of spend keys
	 * @param isViewWallet                If its a view wallet
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
			processTransactionOutputs(block.getTransactionRawCoinbase(), block.getBlockHeight())
				.subscribe(outputs -> {
					inputs.values().addAll(outputs.values());
				});
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
		return 0;
		//TODO: code below will result in null exception
		// return synchronizationStatus.getLastKnownBlockHeight();
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
	public Observable<HashMap<List<Block>, Boolean>> fetchBlocks() throws NodeDeadException {
		var map = new HashMap<List<Block>, Boolean>();

		// fetch more blocks if we haven't got any downloaded yet
		if (storedBlocks != null && storedBlocks.size() == 0) {
			if (!fetchingBlocks) {
				logger.info("No blocks stored, attempting to fetch more.");
			}

			var blocks = downloadBlocks().blockingSingle();

			var successOrBusy = blocks.keySet().iterator().next();
			var shouldSleep = blocks.values().iterator().next();

			// not in the middle of fetching blocks.
			if (!successOrBusy) {
				// seconds since we last got a block
				var diff = (Instant.now().toEpochMilli() - lastDownloadedBlocks.toEpochMilli()) / 1000;

				if (diff > MAX_LAST_FETCHED_BLOCK_INTERVAL) {
					throw new NodeDeadException();
				}
			} else {
				lastDownloadedBlocks = Instant.now();
			}

			map.put(storedBlocks.subList(0, (int) BLOCKS_PER_TICK), shouldSleep);
		}

		return Observable.just(map);
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
		/* Middle of fetching blocks, wait for previous request to complete.
		 * Don't need to sleep. */
		if (fetchingBlocks) {
			return Observable.just(Map.of(true, false));
		}

		fetchingBlocks = true;

		var localDaemonBlockCount = daemon.getLocalDaemonBlockCount();
		var walletBlockCount = getHeight();

		if (localDaemonBlockCount < walletBlockCount) {
			this.fetchingBlocks = false;
			return Observable.just(Map.of(true, true));
		}

		/* Get the checkpoints of the blocks we've got stored, so we can fetch
           later ones. Also use the checkpoints of the previously processed
           ones, in case we don't have any blocks yet. */
		var blockCheckpoints = getWalletSyncDataHashes();

		var blocks = new ArrayList<Block>();
		var topBlock = new TopBlock();
		var walletSyncData = new WalletSyncData();

		try {
			var walletSyncData = daemon.getWalletSyncData(
				blockCheckpoints, startHeight, startTimestamp
			);
		} catch (Exception e) {

		}

		return Observable.empty();
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
