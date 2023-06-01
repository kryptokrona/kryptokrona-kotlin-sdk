package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRpcResponse(
    val jsonrpc: String,
    val result: TransactionResult
)
