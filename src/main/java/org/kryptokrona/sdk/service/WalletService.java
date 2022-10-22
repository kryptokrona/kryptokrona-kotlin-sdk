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
import org.kryptokrona.sdk.block.Block;
import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.exception.daemon.DaemonOfflineException;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.util.Metronome;
import org.kryptokrona.sdk.wallet.SubWallets;
import org.kryptokrona.sdk.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kryptokrona.sdk.config.Config.*;

/**
 * WalletService.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletService {

	private final DaemonImpl daemon;

	private SubWallets subWallets;

	private WalletSynchronizerService walletSynchronizerService;

	private Metronome syncThread;

	private Metronome daemonUpdateThread;

	private Metronome lockedTransactionsCheckThread;

	private boolean started;

	private boolean synced;

	private boolean autoOptimize;

	private boolean shouldPerformAutoOptimize;

	private boolean currentlyOptimizing;

	private boolean currentlyTransacting;

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	public WalletService(
		DaemonImpl daemon
	) {
		this.daemon = daemon;
		this.walletSynchronizerService = new WalletSynchronizerService();
		this.started = false;
		this.autoOptimize = true;
		this.shouldPerformAutoOptimize = true;
		this.currentlyOptimizing = false;
		this.currentlyTransacting = false;
		this.setupMetronomes();
	}

	public void start() throws IOException {
		if (!started) {
			started = true;

			try {
				daemon.init();
				logger.info("Starting the wallet sync process.");

				var st = syncThread.start().publish();
				var dut = daemonUpdateThread.start().publish();
				var ltct = lockedTransactionsCheckThread.start().publish();

				st.subscribe(r -> sync(true).blockingSubscribe());
				dut.subscribe(r -> updateDaemonInfo().blockingSubscribe());
				ltct.subscribe(r -> checkLockedTransactions().blockingSubscribe());

				st.connect();
				dut.connect();
				ltct.connect();
			} catch (NodeDeadException e) {
				logger.error("%s", e);
			}
		}
	}

	public void stop() {
		started = false;
		// daemon.stop();

		syncThread.stop();
		daemonUpdateThread.stop();
		lockedTransactionsCheckThread.stop();

		logger.info("Stopping the wallet sync process.");
	}

	public Observable<Boolean> processBlocks(boolean sleep) {
		walletSynchronizerService.fetchBlocks()
			.blockingSubscribe(data -> {
				var blocks = data.values().iterator().next();
				var shouldSleep = data.keySet().iterator().next();

				if (blocks.size() == 0) {
					// shouldSleep should be a second condition below
					if (sleep && shouldSleep) {
						Thread.sleep(1000);
					}

					// return Observable.just(false);
				}

				for (var block : blocks) {
					logger.info("Processing block " + block.getBlockHeight());

					// forked chain, remove old data
					if (walletSynchronizerService.getHeight() >= block.getBlockHeight()) {
						logger.info("Removing forked transactions.");

						subWallets.removeForkedTransactions(block.getBlockHeight());
					}

					if (block.getBlockHeight() % 5000 == 0 && block.getBlockHeight() != 0) {
						subWallets.pruneSpentInputs(block.getBlockHeight() - 5000);
					}

					/* User can supply us a function to do the processing, possibly
					   utilizing native code for moar speed */
					// var processFunction = external

					var globalIndexes = new HashMap<String, List<Long>>();

					// for (var input : blockInputs)

					// var txData =

					// storeTxData(txData, block.getBlockHeight());

					// walletSynchronizer.dropBlock(block.getBlockHeight(), block.getBlockHash());

					logger.info("Finishing process block " + block.getBlockHeight());
				}
			});

		return Observable.just(true);
	}

	public Observable<Boolean> sync(boolean sleep) {
		try {
			return processBlocks(sleep);
		} catch (Exception e) {
			logger.error("Error processing blocks: " + e);
		}

		return Observable.just(false);
	}

	/**
	 * Update daemon status.
	 *
	 * @return Observable
	 */
	public Observable<Void> updateDaemonInfo() throws IOException, NodeDeadException {
		logger.info("Updating daemon info...");

		daemon.updateDaemonInfo().blockingSingle();

		var walletHeight = walletSynchronizerService.getHeight();
		var networkHeight = daemon.getNetworkBlockCount();

		if (walletHeight >= networkHeight) {

			// synced with the network
			if (!synced) {
				synced = true;
			}

			if (shouldPerformAutoOptimize && autoOptimize) {
				try {
					performAutoOptimize();
				} catch (Exception e) {
					logger.error("Auto-optimize error.");
				}
			}
		}

		return Observable.empty();
	}

	/**
	 * Remove any transactions that have been cancelled.
	 *
	 * @return Observable
	 */
	public Observable<Void> checkLockedTransactions() {
		logger.info("Checking locked transactions...");

		var lockedTxHashes = subWallets.getLockedTransactionHashes();
		var cancelledTxs = walletSynchronizerService.findCancelledTransactions(lockedTxHashes).blockingSingle();

		for (var cancelledTx : cancelledTxs) {
			subWallets.removeCancelledTransaction(cancelledTx);
		}

		return Observable.empty();
	}

	public void setupMetronomes() {
		syncThread = new Metronome(SYNC_THREAD_INTERVAL);
		daemonUpdateThread = new Metronome(DAEMON_UPDATE_INTERVAL);
		lockedTransactionsCheckThread = new Metronome(LOCKED_TRANSACTIONS_CHECK_INTERVAL);
	}

	/**
	 * Creates a new wallet instance with a random key pair.
	 *
	 * @return Wallet
	 */
	public Wallet createWallet() {
		logger.info("New Wallet was created.");
		return new Wallet();
	}

	/**
	 * Since we're going to use optimize() with auto optimizing, and auto
	 * optimizing is enabled by default, we have to ensure we only optimize
	 * a single wallet at once. Otherwise, we'll end up with everyones balance
	 * in the primary wallet.
	 */
	public Observable<Map<List<String>, Long>> optimizeAddress(String address) {
		return Observable.empty();
	}

	/**
	 * Optimizes your wallet as much as possible. It will optimize every single
	 * subwallet correctly, if you have multiple subwallets. Note that this
	 * method does not wait for the funds to return to your wallet before
	 * returning, so, it is likely balances will remain locked.
	 *
	 * Note that if you want to alert the user in real time of the hashes or
	 * number of transactions sent, you can subscribe to the `createdfusiontx`
	 * event. This will be fired every time a fusion transaction is sent.
	 *
	 * You may also want to consider manually creating individual transactions
	 * if you want more control over the process. See [[sendFusionTransactionBasic]].
	 *
	 * This method may take a *very long time* if your wallet is not optimized
	 * at all. It is suggested to not block the UI/mainloop of your program
	 * when using this method.
	 */
	public Observable<Map<ArrayList<String>, Long>> optimize() {
		logger.info("Method optimize called.");

		var numTxsSent = 0L;
		var hashes = new ArrayList<String>();

		for (var address : subWallets.getAddresses()) {
			var data = optimizeAddress(address).blockingSingle();
			numTxsSent += data.values().iterator().next();
			hashes.addAll(data.keySet().iterator().next());
		}

		return Observable.just(Map.of(hashes, numTxsSent));
	}

	public Observable<Void> performAutoOptimize() {
		shouldPerformAutoOptimize = false;

		// already optimizing, don't optimize again
		if (!currentlyOptimizing) {
			currentlyOptimizing = true;

			if (!currentlyTransacting) {
				logger.info("Performing auto optimization.");

				optimize();

				logger.info("Auto optimization complete.");
			}
		}

		// we're done with optimizing
		currentlyOptimizing = false;

		return Observable.empty();
	}

}
