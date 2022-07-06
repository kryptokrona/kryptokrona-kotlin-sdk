package org.kryptokrona.sdk.service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * SynchronizationStatus.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class SynchronizationStatus {

    private List<String> blockHashCheckpoints;

    private List<String> lastKnownBlockHashes;

    private long lastKnownBlockHeight;

    private long lastSavedCheckpointAt;

    public SynchronizationStatus(
            List<String> blockHashCheckpoints,
            List<String> lastKnownBlockHashes,
            long lastKnownBlockHeight,
            long lastSavedCheckpointAt
    ) {
        this.blockHashCheckpoints = blockHashCheckpoints;
        this.lastKnownBlockHashes = lastKnownBlockHashes;
        this.lastKnownBlockHeight = lastKnownBlockHeight;
        this.lastSavedCheckpointAt = lastSavedCheckpointAt;
    }

}
