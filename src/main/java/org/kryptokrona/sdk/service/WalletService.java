package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.wallet.Wallet;
import org.kryptokrona.sdk.wallet.WalletBasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WalletServiceImpl.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

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
}
