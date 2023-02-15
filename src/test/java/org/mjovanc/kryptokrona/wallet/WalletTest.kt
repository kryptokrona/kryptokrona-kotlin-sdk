package org.mjovanc.kryptokrona.wallet;

class WalletTest {

    /*private Daemon daemon;
    private WalletService walletService;*/

    /*@BeforeEach
    void beforeEach() {
        daemon = new DaemonBasic(new HostName("gota.kryptokrona.se:11898"));
        walletService = new WalletService();
    }

    @Test
    @DisplayName("Create Wallet")
    void createWalletTest() throws IOException, NetworkBlockCountException {
        daemon.init().subscribe(System.out::println);
        walletService.createWallet(daemon);
    }

    @Test
    @DisplayName("Start/Stop Wallet")
    void startStopWalletTest() throws IOException, NetworkBlockCountException {
        daemon.init().subscribe(System.out::println);
        Wallet wallet = walletService.createWallet(daemon);
        wallet.start();
        wallet.stop();
    }

    @Test
    @DisplayName("Save Wallet To File")
    void saveWalletToFileTest() throws IOException, NetworkBlockCountException {
        daemon.init().subscribe(System.out::println);
        Wallet wallet = walletService.createWallet(daemon);
        wallet.saveToFile("test.wallet", "test1234");
    }*/
}