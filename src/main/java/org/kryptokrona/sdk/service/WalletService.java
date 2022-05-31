package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.wallet.Wallet;

public interface WalletService {

    /**
     * Creates a new wallet instance with a random key pair.
     *
     * @param daemon : Daemon
     */
    Wallet createWallet(Daemon daemon);
}
