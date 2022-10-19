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

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
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
import java.util.List;
import java.util.concurrent.Flow;

import static org.kryptokrona.sdk.config.Config.*;

/**
 * WalletService.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletService {

	private final Daemon daemon;

	private List<SubWallets> subWallets;

	private WalletSynchronizerService walletSynchronizerService;

	private Metronome syncThread;

	private Metronome daemonUpdateThread;

	private Metronome lockedTransactionsCheckThread;

	private boolean started;

	private boolean synced;

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	public WalletService(
		DaemonImpl daemon
	) {
		this.daemon = daemon;
		this.started = false;
		this.setupMetronomes();
	}

	public void start() throws IOException {
		if (!started) {
			started = true;

			try {
				daemon.init();
				logger.info("Starting the wallet sync process.");

				ConnectableFlowable<Long> st = syncThread.start().publish();
				ConnectableFlowable<Long> dut = daemonUpdateThread.start().publish();
				ConnectableFlowable<Long> ltct = lockedTransactionsCheckThread.start().publish();

				st.subscribe(result -> {
					System.out.println("st");
					System.out.println(result);
					sync(true).subscribe();
				});

				dut.subscribe(result -> {
					System.out.println("dut");
					System.out.println(result);
					// updateDaemonInfo()
				});

				ltct.subscribe(result -> {
					System.out.println("ltct");
					System.out.println(result);
					// checkLockedTransactions()
				});

				st.connect();
				dut.connect();
				ltct.connect();

			} catch (NetworkBlockCountException | NodeDeadException | DaemonOfflineException e) {
				logger.error("%s", e);
			}
		}
	}

	public void stop() {
		logger.info("Stopping the wallet sync process.");
		started = false;
		// daemon.stop();

		syncThread.stop();
		daemonUpdateThread.stop();
		lockedTransactionsCheckThread.stop();
	}

	public Flowable<Boolean> processBlocks(boolean sleep) {
		logger.info("Processing blocks...");
		return Flowable.just(true);
	}

	public Flowable<Boolean> sync(boolean sleep) {
		try {
			return processBlocks(sleep);
		} catch (Exception e) {
			logger.error("Error processing blocks: " + e);
		}

		return Flowable.just(false);
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

}
