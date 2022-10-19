package org.kryptokrona.sdk;

import inet.ipaddr.HostName;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.service.WalletService;
import org.kryptokrona.sdk.wallet.Wallet;

import java.io.IOException;

public class Example {
	public static void main(String[] args) throws IOException, InterruptedException {
		// initialize a daemon
		var daemon = new DaemonImpl(new HostName("swepool.org:11898"), false);

		// initialize a wallet service
		var walletService = new WalletService(daemon);

		// start wallet service sync
		walletService.start();

		// create a new wallet
		// var wallet = walletService.createWallet();

		// save the wallet to a file
		// walletService.saveWalletToFile(wallet, "mjovanc");

		// sleep to keep processing more blocks before we stop it
		Thread.sleep(50000);

		// stop the wallet sync
		walletService.stop();
	}
}
