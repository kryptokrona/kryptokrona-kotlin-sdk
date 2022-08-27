package org.kryptokrona.sdk;

import inet.ipaddr.HostName;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.model.wallet.Wallet;
import org.kryptokrona.sdk.service.WalletService;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		DaemonImpl daemon = new DaemonImpl(new HostName("pool.gamersnest.org:11898"), false);

		WalletService walletService = new WalletService(daemon);
		walletService.start();
		daemon.getGlobalIndexesForRange(1, 2);
		Wallet wallet = walletService.createWallet();
		walletService.stop();
	}
}
