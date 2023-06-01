package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class BlocksListResponse(
    val jsonrpc: String,
    val result: BlocksListResult
)
