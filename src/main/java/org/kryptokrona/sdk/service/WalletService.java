package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.wallet.Wallet;

/**
 * WalletService.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public interface WalletService {

    /**
     * Creates a new wallet instance with a random key pair.
     *
     * @param daemon : Daemon
     */
    Wallet createWallet(Daemon daemon);
}
