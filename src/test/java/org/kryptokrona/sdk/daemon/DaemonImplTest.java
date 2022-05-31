package org.kryptokrona.sdk.daemon;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kryptokrona.sdk.service.WalletService;
import org.kryptokrona.sdk.service.WalletServiceImpl;

import java.io.IOException;

public class DaemonImplTest {

    @Test
    @DisplayName("Initializing Daemon")
    void initializeDaemonTest() throws IOException {
        Gson gson = new Gson();

        Daemon daemon = new DaemonImpl("gota.kryptokrona.se", 11898);
        daemon.init().subscribe(
                System.out::println
        );

        //TODO: remove these lines below later
        WalletService ws = new WalletServiceImpl();
        ws.createWallet(daemon);

        // List<Score> scoreData = gson.fromJson()
    }

}
