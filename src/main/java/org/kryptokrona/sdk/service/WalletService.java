package org.kryptokrona.sdk.service;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.config.Config;
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

/**
 * WalletService.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletService {

	private Daemon daemon;

	private Config config;

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

				Observable.merge(
						syncThread.start(),
						daemonUpdateThread.start(),
						lockedTransactionsCheckThread.start()
				).subscribe();

				logger.info("Starting the wallet sync process.");
			} catch (NetworkBlockCountException | NodeDeadException | DaemonOfflineException e) {
				logger.error("%s", e);
			}

			// merge obserables
            /*await Promise.all([
                    this.syncThread.start(),
                    this.daemonUpdateThread.start(),
                    this.lockedTransactionsCheckThread.start()
            ]);*/
		}
	}

	public void stop() {
		logger.info("Stopping the wallet sync process.");
		started = false;
		// daemon.stop();

        syncThread.stop().subscribe();
		daemonUpdateThread.stop().subscribe();
		lockedTransactionsCheckThread.stop().subscribe();
	}

	public Observable<Boolean> processBlocks(boolean sleep) {
		return Observable.just(true);
	}

	public Observable<Boolean> sync(boolean sleep) {
		try {
			return processBlocks(sleep);
		} catch(Exception e) {
			logger.error("Error processing blocks: " + e);
		}

		return Observable.just(false);
	}

	public void setupMetronomes() {
		syncThread = new Metronome(Config.SYNC_THREAD_INTERVAL);
		daemonUpdateThread = new Metronome(Config.DAEMON_UPDATE_INTERVAL);
		lockedTransactionsCheckThread = new Metronome(Config.LOCKED_TRANSACTIONS_CHECK_INTERVAL);

		syncThread.start().subscribe(tick -> {
			System.out.println(tick);
		});
		daemonUpdateThread.start().subscribe(tick -> {
			System.out.println(tick);
		});
		lockedTransactionsCheckThread.start().subscribe(tick -> {
			System.out.println(tick);
		});
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
