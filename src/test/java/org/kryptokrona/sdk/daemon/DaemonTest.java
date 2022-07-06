package org.kryptokrona.sdk.daemon;

import inet.ipaddr.HostName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;

import java.io.IOException;

public class DaemonTest {

    @Test
    @DisplayName("Initializing Daemon")
    void initializeDaemonTest() throws IOException, NetworkBlockCountException {
        Daemon daemon = new DaemonBasic(new HostName("gota.kryptokrona.se:11898"));
        daemon.init().subscribe(System.out::println);
    }

}
