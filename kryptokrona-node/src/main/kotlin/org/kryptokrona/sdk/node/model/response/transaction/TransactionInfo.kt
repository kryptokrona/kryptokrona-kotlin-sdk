package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionInfo(
    @SerialName("amount_out") val amountOut: Long,
    val fee: Long,
    val hash: String,
    val size: Int
)

//TODO: we should probably squash this together with TransactionInfo2
