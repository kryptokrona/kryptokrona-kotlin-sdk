package org.kryptokrona.sdk.node.model.response.transaction

import kotlinx.serialization.Serializable

@Serializable
data class TransactionsPoolResponse(
    val jsonrpc: String,
    val result: TransactionsPoolResult
)
