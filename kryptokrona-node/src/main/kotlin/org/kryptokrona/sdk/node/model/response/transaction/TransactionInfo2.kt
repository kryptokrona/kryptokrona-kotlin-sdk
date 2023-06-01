package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionInfo2(
    val extra: String,
    @SerialName("unlock_time") val unlockTime: Int,
    val version: Int,
    val vin: List<Vin>,
    val vout: List<Vout>
)
