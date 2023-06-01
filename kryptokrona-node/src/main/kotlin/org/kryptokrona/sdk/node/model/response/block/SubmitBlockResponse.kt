package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class SubmitBlockResponse(
    val jsonrpc: String,
    val result: SubmitBlockResult
)
