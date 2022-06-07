package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.wallet.Wallet;
import org.kryptokrona.sdk.wallet.WalletImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WalletServiceImpl.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletServiceImpl implements WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Override
    public Wallet createWallet(Daemon daemon) {
        logger.debug("New Wallet was created.");
        return new WalletImpl();
    }
}
