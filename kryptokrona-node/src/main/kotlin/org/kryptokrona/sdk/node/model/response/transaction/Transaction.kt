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

package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kryptokrona.sdk.node.model.response.TransactionResponse

@Serializable
data class Transaction(
    @SerialName("transactionPrefixInfo.txHash") val transactionHash: String,
    @SerialName("transactionPrefixInfo.txPrefix") val transactionPrefix: TransactionPrefix
)

@Serializable
data class TransactionDetailsHashes(
    val status: String,
    val transactions: List<TransactionResponse>
)

@Serializable
data class TransactionHashesPaymentId(
    val status: String,
    val transactionHashes: List<String>
)

@Serializable
data class TransactionPrefix(
    val extra: String,
    @SerialName("unlock_time") val unlockTime: Long,
    val version: Long,
    val vin: List<TransactionPrefixVin>,
    val vout: List<TransactionPrefixVout>
)

@Serializable
data class TransactionPrefixVin(
    val type: String,
    val value: TransactionPrefixVinValue
)

@Serializable
data class TransactionPrefixVinValue(
    val amount: Long,
    @SerialName("k_image") val keyImage: String,
    @SerialName("key_offsets") val keyOffsets: List<Long>
)

@Serializable
data class TransactionPrefixVout(
    val amount: Long,
    val target: TransactionPrefixVoutTarget
)

@Serializable
data class TransactionPrefixVoutTarget(
    val data: TransactionPrefixVoutTargetData,
    val type: String
)

@Serializable
data class TransactionPrefixVoutTargetData(
    val key: String
)
