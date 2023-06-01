package org.kryptokrona.sdk.node.model.request.block

import kotlinx.serialization.Serializable

@Serializable
data class BlocksListRequest(
    val jsonrpc: String = "2.0",
    val method: String,
    val params: Map<String, Int>
)

