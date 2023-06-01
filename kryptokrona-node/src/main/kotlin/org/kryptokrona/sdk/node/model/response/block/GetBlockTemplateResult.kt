package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockTemplateResult(
    val blocktemplate_blob: String,
    val difficulty: Int,
    val height: Int,
    val reserved_offset: Int,
    val status: String
)
