package org.kryptokrona.sdk.model.http;

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
public class NodeInfo {

    private int                 altBlocksCount;
    private int                 difficulty;
    private int                 greyPeerlistSize;
    private int                 hashrate;
    private int                 height;
    private int                 incomingConnectionsCount;
    private int                 lastKnownBlockIndex;
    private int                 majorVersion;
    private int                 minorVersion;
    private int                 networkHeight;
    private int                 outgoingConnectionsCount;
    private int                 startTime;
    private String              status;
    private int                 supportedHeight;
    private boolean             synced;
    private boolean             testnet;
    private int                 txCount;
    private int                 txPoolSize;
    private List<Integer>       upgradeHeights;
    private String              version;
    private int                 whitePeerlistSize;

    public NodeInfo(
            int altBlocksCount, int difficulty, int greyPeerlistSize, int hashrate, int height,
            int incomingConnectionsCount, int lastKnownBlockIndex, int majorVersion, int minorVersion,
            int networkHeight, int outgoingConnectionsCount, int startTime, String status,
            int supportedHeight, boolean synced, boolean testnet, int txCount, int txPoolSize,
            List<Integer> upgradeHeights, String version, int whitePeerlistSize
    ) {
        this.altBlocksCount             = altBlocksCount;
        this.difficulty                 = difficulty;
        this.greyPeerlistSize           = greyPeerlistSize;
        this.hashrate                   = hashrate;
        this.height                     = height;
        this.incomingConnectionsCount   = incomingConnectionsCount;
        this.lastKnownBlockIndex        = lastKnownBlockIndex;
        this.majorVersion               = majorVersion;
        this.minorVersion               = minorVersion;
        this.networkHeight              = networkHeight;
        this.outgoingConnectionsCount   = outgoingConnectionsCount;
        this.startTime                  = startTime;
        this.status                     = status;
        this.supportedHeight            = supportedHeight;
        this.synced                     = synced;
        this.testnet                    = testnet;
        this.txCount                    = txCount;
        this.txPoolSize                 = txPoolSize;
        this.upgradeHeights             = upgradeHeights;
        this.version                    = version;
        this.whitePeerlistSize          = whitePeerlistSize;
    }
}
