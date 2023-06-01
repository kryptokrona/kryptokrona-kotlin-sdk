package org.kryptokrona.sdk.node.model.request.transaction

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class TransactionsPoolRequest(
    val jsonrpc: String = "2.0",
    val method: String,
    @Contextual val params: Map<String, @Contextual Any>
)
