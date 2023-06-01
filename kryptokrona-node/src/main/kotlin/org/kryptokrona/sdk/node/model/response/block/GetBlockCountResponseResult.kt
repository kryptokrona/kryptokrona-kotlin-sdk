package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockCountResponseResult(
    val count: Int,
    val status: String
)
