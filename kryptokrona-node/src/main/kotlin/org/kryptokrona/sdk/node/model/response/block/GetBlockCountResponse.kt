package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockCountResponse(
    val jsonrpc: String,
    val result: GetBlockCountResponseResult
)
