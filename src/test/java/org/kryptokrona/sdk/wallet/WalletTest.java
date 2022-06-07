package org.kryptokrona.sdk.wallet;

import com.google.gson.Gson;
import inet.ipaddr.HostName;
import inet.ipaddr.HostNameException;
import org.junit.jupiter.api.*;
import org.kryptokrona.sdk.daemon.Daemon;
import org.kryptokrona.sdk.daemon.DaemonImpl;
import org.kryptokrona.sdk.service.WalletService;
import org.kryptokrona.sdk.service.WalletServiceImpl;

import java.io.IOException;

class WalletTest {

    private Daemon daemon;
    private WalletService walletService;

    @BeforeEach
    void beforeEach() {
        daemon = new DaemonImpl(new HostName("gota.kryptokrona.se:11898"));
        walletService = new WalletServiceImpl();
    }

    @Test
    @DisplayName("Create Wallet")
    void createWalletTest() throws IOException {
        daemon.init().subscribe(System.out::println);
        walletService.createWallet(daemon);
    }

    @Test
    @DisplayName("Start/Stop Wallet")
    void startStopWalletTest() throws IOException {
        daemon.init().subscribe(System.out::println);
        Wallet wallet = walletService.createWallet(daemon);
        wallet.start();
        wallet.stop();
    }

    @Test
    @DisplayName("Save Wallet To File")
    void saveWalletToFileTest() throws IOException {
        daemon.init().subscribe(System.out::println);
        Wallet wallet = walletService.createWallet(daemon);
        wallet.saveToFile("test.wallet", "test1234");
    }
}