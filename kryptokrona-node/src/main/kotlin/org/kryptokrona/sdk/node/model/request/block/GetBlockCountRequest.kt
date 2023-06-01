package org.kryptokrona.sdk.node.model.request.block

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class GetBlockCountRequest(
    val jsonrpc: String = "2.0",
    val method: String,
    @Contextual val params: Map<String, @Contextual Any>
)
