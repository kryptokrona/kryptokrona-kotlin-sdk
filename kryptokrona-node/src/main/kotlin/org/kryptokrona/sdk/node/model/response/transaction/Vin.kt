package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.Serializable

@Serializable
data class Vin(
    val type: String,
    val value: VinValue
)

@Serializable
data class VinValue(
    val height: Int
)

@Serializable
data class Vout(
    val amount: Long,
    val target: Target
)

@Serializable
data class Target(
    val data: TargetData,
    val type: String
)

@Serializable
data class TargetData(
    val key: String
)