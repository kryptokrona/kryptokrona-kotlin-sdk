// Copyright (c) 2022-2023, The Kryptokrona Developers
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.mjovanc.kryptokrona.service;

import lombok.Getter;
import lombok.Setter;
import org.mjovanc.kryptokrona.config.Constants;

import java.util.LinkedList;
import java.util.Objects;

/**
 * SynchronizationStatus.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class SynchronizationStatus {

	private LinkedList<String> blockHashCheckpoints;
	private LinkedList<String> lastKnownBlockHashes;
	private long lastKnownBlockHeight;
	private long lastSavedCheckpointAt;

	public SynchronizationStatus() {
		this.blockHashCheckpoints = new LinkedList<>();
		this.lastKnownBlockHashes = new LinkedList<>();
		this.lastKnownBlockHeight = 0;
		this.lastSavedCheckpointAt = 0;
	}

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

		// hash already exists
		if (lastKnownBlockHashes.size() > 0 && Objects.equals(lastKnownBlockHashes.get(0), blockHash)) {
			return;
		}

		/*
		 * If we're at a checkpoint height, add the hash to the infrequent
		 * checkpoints (at the beginning of the queue)
		 */
		if ((lastSavedCheckpointAt + Constants.BLOCK_HASH_CHECKPOINTS_INTERVAL) < blockHeight) {
			lastSavedCheckpointAt = blockHeight;
			blockHashCheckpoints.addFirst(blockHash);
		}

		this.lastKnownBlockHashes.addFirst(blockHash);

		// if we're exceeding capacity, remove the last (oldest) hash
		if (lastKnownBlockHashes.size() > Constants.LAST_KNOWN_BLOCK_HASHES_SIZE) {
			lastKnownBlockHashes.pollLast();
		}
	}
}
