package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kryptokrona.sdk.node.model.response.transaction.TransactionInfo

@Serializable
data class Block2Info(
    @SerialName("alreadyGeneratedCoins") val alreadyGeneratedCoins: String,
    @SerialName("alreadyGeneratedTransactions") val alreadyGeneratedTransactions: Int,
    @SerialName("baseReward") val baseReward: Long,
    @SerialName("blockSize") val blockSize: Int,
    val depth: Int,
    val difficulty: Int,
    @SerialName("effectiveSizeMedian") val effectiveSizeMedian: Int,
    val hash: String,
    val height: Int,
    @SerialName("major_version") val majorVersion: Int,
    @SerialName("minor_version") val minorVersion: Int,
    val nonce: Int,
    @SerialName("orphan_status") val orphanStatus: Boolean,
    val penalty: Double,
    @SerialName("prev_hash") val prevHash: String,
    val reward: Long,
    @SerialName("sizeMedian") val sizeMedian: Int,
    val timestamp: Int,
    @SerialName("totalFeeAmount") val totalFeeAmount: Long,
    val transactions: List<TransactionInfo>,
    @SerialName("transactionsCumulativeSize") val transactionsCumulativeSize: Int
)
