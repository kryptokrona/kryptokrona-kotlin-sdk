package org.kryptokrona.sdk.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Info.java
 *
 * Represents a model to store information related to the
 * network and daemon connection.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Info {
    private int altBlocksCount;
    private int difficulty;
    private int greyPeerlistSize;
    private int hashrate;
    private int height;
    private int incomingConnectionsCount;
    private int lastKnownBlockIndex;
    private int majorVersion;
    private int minorVersion;
    private int networkHeight;
    private int outgoingConnectionsCount;
    private int startTime;
    private String status;
    private int supportedHeight;
    private boolean synced;
    private boolean testnet;
    private int txCount;
    private int txPoolSize;
    private List<Integer> upgradeHeights;
    private String version;
    private int whitePeerlistSize;
}
