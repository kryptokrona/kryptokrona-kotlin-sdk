package org.kryptokrona.sdk.node.model.response.block

import kotlinx.serialization.Serializable

@Serializable
data class GetCurrencyIdResult(
    val currency_id_blob: String
)
