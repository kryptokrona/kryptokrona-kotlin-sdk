package org.kryptokrona.sdkexample;

public class Main {
    public static void main(String[] args) {
        // initialize a daemon
        DaemonBasic daemon = new DaemonBasic(new HostName("gota.kryptokrona.se:11898"));

        // initialize a wallet service
        WalletService walletService = new WalletService(daemon);

        // start wallet service sync
        walletService.start();

        // create a new wallet
        WalletBasic wallet = walletService.createWallet(daemon);

        // save the wallet to a file
        walletService.saveWalletToFile(wallet, "mjovanc");

        // stop the wallet sync
        walletService.stop();
    }
}