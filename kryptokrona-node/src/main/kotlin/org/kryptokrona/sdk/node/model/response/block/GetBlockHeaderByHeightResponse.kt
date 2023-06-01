package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockHeaderByHeightResponse(
    val jsonrpc: String,
    val result: GetBlockHeaderByHeightResult
)
