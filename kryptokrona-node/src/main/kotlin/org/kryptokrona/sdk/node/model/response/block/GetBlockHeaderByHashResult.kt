package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockHeaderByHashResult(
    val block_header: BlockHeader,
    val status: String
)
