package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class BlockInfo(
    val cumul_size: Int,
    val difficulty: Int,
    val hash: String,
    val height: Int,
    val timestamp: Int,
    val tx_count: Int
)
