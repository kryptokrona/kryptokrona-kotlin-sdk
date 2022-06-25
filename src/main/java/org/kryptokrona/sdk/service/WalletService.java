package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.exception.NetworkBlockCountException;
import org.kryptokrona.sdk.wallet.Wallet;
import org.kryptokrona.sdk.wallet.WalletBasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * WalletServiceImpl.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletService {

    private Daemon daemon;

    private boolean started;

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    public WalletService(Daemon daemon) {
        this.daemon = daemon;
        this.started = false;
    }

    public void start() throws IOException {
        if (!started) {
            logger.info("Starting the wallet sync process.");
            started = true;

            try {
                daemon.init();
            } catch (NetworkBlockCountException e) {
                logger.error("Network block count cannot be 0.");
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

        /*await this.syncThread.stop();
        await this.daemonUpdateThread.stop();
        await this.lockedTransactionsCheckThread.stop();*/
    }

    /**
     * Creates a new wallet instance with a random key pair.
     *
     * @param daemon : Daemon
     * @return Wallet
     */
    public WalletBasic createWallet(Daemon daemon) {
        logger.info("New Wallet was created.");
        return new WalletBasic();
    }

    public boolean saveWalletToFile(Wallet wallet, String password) {
        // save wallet to wallet.sqlite3
        return true;
    }
}
