package org.kryptokrona.sdk.daemon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Daemon {

    private String hostname;
    private int port;

    public Daemon(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    void init() {
        //TODO: should use the given hostname and port and make connection to node.

    }

}
