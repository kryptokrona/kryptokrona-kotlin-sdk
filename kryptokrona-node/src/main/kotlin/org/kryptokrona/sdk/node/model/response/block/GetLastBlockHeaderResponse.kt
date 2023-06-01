package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetLastBlockHeaderResponse(
    val jsonrpc: String,
    val result: GetLastBlockHeaderResult
)
