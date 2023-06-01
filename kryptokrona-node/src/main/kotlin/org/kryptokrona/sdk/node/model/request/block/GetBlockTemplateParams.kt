package org.kryptokrona.sdk.node.model.request.block

import kotlinx.serialization.Serializable

@Serializable
data class GetBlockTemplateParams(
    val reserve_size: Int,
    val wallet_address: String
)
