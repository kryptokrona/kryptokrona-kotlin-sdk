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
// LongERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Block info.
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 */
@Serializable
data class BlockInfo(
    @SerialName("alreadyGeneratedCoins") val alreadyGeneratedCoins: String,
    @SerialName("alreadyGeneratedTransactions") val alreadyGeneratedTransactions: Long,
    @SerialName("baseReward") val baseReward: Long,
    @SerialName("blockSize") val blockSize: Long,
    @SerialName("depth") val depth: Long,
    @SerialName("difficulty") val difficulty: Long,
    @SerialName("effectiveSizeMedian") val effectiveSizeMedian: Long,
    @SerialName("hash") val hash: String,
    @SerialName("height") val height: Long,
    @SerialName("major_version") val majorVersion: Long,
    @SerialName("minor_version") val minorVersion: Long,
    @SerialName("nonce") val nonce: Long,
    @SerialName("orphan_status") val orphanStatus: Boolean,
    @SerialName("penalty") val penalty: Double,
    @SerialName("prev_hash") val prevHash: String,
    @SerialName("reward") val reward: Long,
    @SerialName("sizeMedian") val sizeMedian: Long,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("totalFeeAmount") val totalFeeAmount: Long,
    @SerialName("transactions") val transactions: List<BlockTransaction>,
    @SerialName("transactionsCumulativeSize") val transactionsCumulativeSize: Long
)

@Serializable
data class BlockInfo2(
    @SerialName("cumul_size") val cumulSize: Long,
    @SerialName("difficulty") val difficulty: Long,
    @SerialName("hash") val hash: String,
    @SerialName("height") val height: Long,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("tx_count") val txCount: Long
)

