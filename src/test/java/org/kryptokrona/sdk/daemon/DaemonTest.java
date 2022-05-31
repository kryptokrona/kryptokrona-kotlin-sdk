package org.kryptokrona.sdk.daemon;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DaemonTest {

    @Test
    @DisplayName("Initializing Daemon")
    void initializeDaemonTest() throws IOException {
        Gson gson = new Gson();

        Daemon daemon = new Daemon("gota.kryptokrona.se", 11898);
        daemon.init().subscribe(
                System.out::println
        );

        // List<Score> scoreData = gson.fromJson()
    }

}
