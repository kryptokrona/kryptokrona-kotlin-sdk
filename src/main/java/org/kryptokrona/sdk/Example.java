package org.kryptokrona.sdk;

import inet.ipaddr.HostName;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.service.WalletService;
import org.kryptokrona.sdk.wallet.Wallet;

import java.io.IOException;

public class Example {
	public static void main(String[] args) throws IOException, NodeDeadException {
		// initialize a daemon
		var daemon = new DaemonImpl(new HostName("swepool.org:11898"), false);

		// initialize a wallet service
		var walletService = new WalletService(daemon);

		// start wallet service sync
		walletService.start();

		// create a new wallet
		Wallet wallet = walletService.createWallet();

		// save the wallet to a file
		// walletService.saveWalletToFile(wallet, "mjovanc");

		// stop the wallet sync
		// walletService.stop();
	}
}
