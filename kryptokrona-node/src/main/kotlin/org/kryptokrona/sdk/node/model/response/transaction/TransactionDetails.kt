package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDetails(
    @SerialName("amount_out") val amountOut: Long,
    val fee: Long,
    val hash: String,
    val mixin: Int,
    @SerialName("paymentId") val paymentID: String,
    val size: Int
)
