package org.kryptokrona.sdk.node.model.request.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRequest(
    val jsonrpc: String = "2.0",
    val method: String,
    val params: Map<String, String>
)
