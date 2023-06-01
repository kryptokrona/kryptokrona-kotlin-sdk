package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class BlockResult(
    val block: BlockInfo,
    val status: String
)
