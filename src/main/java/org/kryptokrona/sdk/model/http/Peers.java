package org.kryptokrona.sdk.model.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Peers.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Peers {

    private List<String>    peers;
    private String          status;

    public Peers(List<String> peers, String status) {
        this.peers  = peers;
        this.status = status;
    }
}
