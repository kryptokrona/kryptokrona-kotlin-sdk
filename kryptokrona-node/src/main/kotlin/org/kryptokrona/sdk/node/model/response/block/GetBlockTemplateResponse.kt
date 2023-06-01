package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockTemplateResponse(
    val jsonrpc: String,
    val result: GetBlockTemplateResult
)
