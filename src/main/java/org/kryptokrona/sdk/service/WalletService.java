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

    public void start() throws NetworkBlockCountException, IOException, InterruptedException {
        logger.debug("Starting the wallet sync process.");
        started = true;
        daemon.init();
    }

    public void stop() {
        logger.debug("Stopping the wallet sync process.");
        started = false;
        // daemon.stop();
    }

    /**
     * Creates a new wallet instance with a random key pair.
     *
     * @param daemon : Daemon
     * @return Wallet
     */
    public Wallet createWallet(Daemon daemon) {
        logger.debug("New Wallet was created.");
        return new WalletBasic();
    }

    public boolean saveWalletToFile(String password) {
        // save wallet to wallet.sqlite3
        return true;
    }
}
