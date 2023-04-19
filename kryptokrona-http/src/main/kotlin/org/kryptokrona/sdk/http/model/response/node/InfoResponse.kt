// Copyright (c) 2022-2023, The Kryptokrona Developers
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

package org.kryptokrona.sdk.http.model.response.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Node info
 */
@Serializable
data class InfoResponse(

    @SerialName("alt_blocks_count")
    val altBlocksCount: Long,

    val difficulty: Long,

    @SerialName("grey_peerlist_size")
    val greyPeerlistSize: Long,

    val hashrate: Long,
    val height: Long,

    @SerialName("incoming_connections_count")
    val incomingConnectionsCount: Long,

    @SerialName("last_known_block_index")
    val lastKnownBlockIndex: Long,

    @SerialName("major_version")
    val majorVersion: Int,

    @SerialName("minor_version")
    val minorVersion: Int,

    @SerialName("network_height")
    val networkHeight: Long,

    @SerialName("outgoing_connections_count")
    val outgoingConnectionsCount: Long,

    @SerialName("start_time")
    val startTime: Long,

    val status: String,

    @SerialName("supported_height")
    val supportedHeight: Long,

    val synced: Boolean,
    val testnet: Boolean,

    @SerialName("tx_count")
    val txCount: Long,

    @SerialName("tx_pool_size")
    val txPoolSize: Long,

    @SerialName("upgrade_heights")
    val upgradeHeights: List<Int>,

    val version: String,

    @SerialName("white_peerlist_size")
    val whitePeerlistSize: Long
)
