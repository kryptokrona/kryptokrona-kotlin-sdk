package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.model.wallet.Wallet;

import org.kryptokrona.sdk.model.wallet.WalletSub;
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

    private Daemon                      daemon;
    private Config                      config;
    private List<WalletSub>             subWallets;
    private WalletSynchronizerService   walletSynchronizerService;
    private boolean                     started;
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    public WalletService(
            DaemonImpl daemon,
            List<WalletSub> subWallets,
            WalletSynchronizerService walletSynchronizerService
    ) {
        this.daemon = daemon;
        this.subWallets = subWallets;
        this.walletSynchronizerService = walletSynchronizerService;
        this.started = false;
    }

    public void start() throws IOException {
        if (!started) {
            logger.info("Starting the wallet sync process.");
            started = true;

            try {
                daemon.init();
            } catch (NetworkBlockCountException | NodeDeadException e) {
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
    public Wallet createWallet(Daemon daemon) {
        logger.info("New Wallet was created.");
        return new Wallet();
    }

    public boolean saveWalletToFile(Wallet wallet, String password) {
        // save wallet to wallet.sqlite3
        return true;
    }
}
