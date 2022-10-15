// Copyright (c) 2022-2022, The Kryptokrona Project
//
// Written by Marcus Cvjeticanin
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

package org.kryptokrona.sdk.model.http;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Info.java
 * <p>
 * Represents a model to store information related to the
 * network and daemon connection.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class NodeInfo {

	@SerializedName("alt_blocks_count")
	private int altBlocksCount;

	private int difficulty;
	@SerializedName("grey_peerlist_size")
	private int greyPeerlistSize;

	private int hashrate;

	private int height;

	@SerializedName("incoming_connections_count")
	private int incomingConnectionsCount;

	@SerializedName("last_known_block_index")
	private int lastKnownBlockIndex;

	@SerializedName("major_version")
	private int majorVersion;

	@SerializedName("minor_version")
	private int minorVersion;

	@SerializedName("network_height")
	private int networkHeight;

	@SerializedName("outgoing_connections_count")
	private int outgoingConnectionsCount;

	@SerializedName("start_time")
	private int startTime;

	private String status;

	@SerializedName("supported_height")
	private int supportedHeight;

	private boolean synced;

	private boolean testnet;

	@SerializedName("tx_count")
	private int txCount;

	@SerializedName("tx_pool_size")
	private int txPoolSize;

	@SerializedName("upgrade_heights")
	private List<Integer> upgradeHeights;

	private String version;

	@SerializedName("white_peerlist_size")
	private int whitePeerlistSize;

	public NodeInfo(
			int altBlocksCount, int difficulty, int greyPeerlistSize, int hashrate, int height,
			int incomingConnectionsCount, int lastKnownBlockIndex, int majorVersion, int minorVersion,
			int networkHeight, int outgoingConnectionsCount, int startTime, String status,
			int supportedHeight, boolean synced, boolean testnet, int txCount, int txPoolSize,
			List<Integer> upgradeHeights, String version, int whitePeerlistSize
	) {
		this.altBlocksCount = altBlocksCount;
		this.difficulty = difficulty;
		this.greyPeerlistSize = greyPeerlistSize;
		this.hashrate = hashrate;
		this.height = height;
		this.incomingConnectionsCount = incomingConnectionsCount;
		this.lastKnownBlockIndex = lastKnownBlockIndex;
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.networkHeight = networkHeight;
		this.outgoingConnectionsCount = outgoingConnectionsCount;
		this.startTime = startTime;
		this.status = status;
		this.supportedHeight = supportedHeight;
		this.synced = synced;
		this.testnet = testnet;
		this.txCount = txCount;
		this.txPoolSize = txPoolSize;
		this.upgradeHeights = upgradeHeights;
		this.version = version;
		this.whitePeerlistSize = whitePeerlistSize;
	}
}
