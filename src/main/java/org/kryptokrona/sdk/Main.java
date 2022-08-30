package org.kryptokrona.sdk;

import inet.ipaddr.HostName;
import org.kryptokrona.sdk.daemon.DaemonImpl;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		var daemon = new DaemonImpl(new HostName("mjovanc.kryptokrona.se:11898"), false);

		// var walletService = new WalletService(daemon);
		// walletService.start();
		// daemon.updateFeeInfo();

		// var wallet = walletService.createWallet();
		// walletService.stop();
	}
}
