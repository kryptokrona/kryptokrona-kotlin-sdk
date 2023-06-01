package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class BlockHeader(
    val block_size: Int,
    val depth: Int,
    val difficulty: Long,
    val hash: String,
    val height: Int,
    val major_version: Int,
    val minor_version: Int,
    val nonce: Long,
    val num_txes: Int,
    val orphan_status: Boolean,
    val prev_hash: String,
    val reward: Long,
    val timestamp: Long
)
