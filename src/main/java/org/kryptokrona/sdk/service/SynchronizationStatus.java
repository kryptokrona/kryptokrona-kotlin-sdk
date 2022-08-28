package org.kryptokrona.sdk.service;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Objects;

import static org.kryptokrona.sdk.config.Constants.BLOCK_HASH_CHECKPOINTS_INTERVAL;
import static org.kryptokrona.sdk.config.Constants.LAST_KNOWN_BLOCK_HASHES_SIZE;

/**
 * SynchronizationStatus.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class SynchronizationStatus {

    private LinkedList<String>      blockHashCheckpoints;
    private LinkedList<String>      lastKnownBlockHashes;
    private long                    lastKnownBlockHeight;
    private long                    lastSavedCheckpointAt;

    public SynchronizationStatus(
            LinkedList<String> blockHashCheckpoints,
            LinkedList<String> lastKnownBlockHashes,
            long lastKnownBlockHeight,
            long lastSavedCheckpointAt
    ) {
        this.blockHashCheckpoints = blockHashCheckpoints;
        this.lastKnownBlockHashes = lastKnownBlockHashes;
        this.lastKnownBlockHeight = lastKnownBlockHeight;
        this.lastSavedCheckpointAt = lastSavedCheckpointAt;
    }

    public void storeBlockHash(long blockHeight, String blockHash) {
        this.lastKnownBlockHeight = blockHeight;

        // Hash already exists
        if (lastKnownBlockHashes.size() > 0 && Objects.equals(lastKnownBlockHashes.get(0), blockHash)) {
            return;
        }

        /*
         * If we're at a checkpoint height, add the hash to the infrequent
         * checkpoints (at the beginning of the queue)
         */
        if ((lastSavedCheckpointAt + BLOCK_HASH_CHECKPOINTS_INTERVAL) < blockHeight) {
            lastSavedCheckpointAt = blockHeight;
            blockHashCheckpoints.addFirst(blockHash);
        }

        this.lastKnownBlockHashes.addFirst(blockHash);

        // If we're exceeding capacity, remove the last (oldest) hash
        if (lastKnownBlockHashes.size() > LAST_KNOWN_BLOCK_HASHES_SIZE) {
            lastKnownBlockHashes.pollLast();
        }
    }
}
