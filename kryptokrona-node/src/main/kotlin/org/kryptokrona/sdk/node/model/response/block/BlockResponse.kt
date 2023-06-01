package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class BlockResponse(
    val jsonrpc: String,
    val result: BlockResult
)
