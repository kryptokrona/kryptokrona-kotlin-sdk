package org.kryptokrona.sdk.service;

import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.wallet.Wallet;
import org.kryptokrona.sdk.wallet.WalletImpl;

public class WalletServiceImpl implements WalletService {

    @Override
    public Wallet createWallet(Daemon daemon) {
        return new WalletImpl();
    }
}
