package org.kryptokrona.sdk.daemon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DaemonTest {

    @Test
    @DisplayName("Initializing Daemon")
    void initializeDaemonTest() {
        Daemon daemon = new Daemon("gota.kryptokrona.se", 11898);
        daemon.init();
    }

}
