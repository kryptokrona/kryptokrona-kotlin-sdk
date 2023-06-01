package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetCurrencyIdResponse(
    val jsonrpc: String,
    val result: GetCurrencyIdResult
)
