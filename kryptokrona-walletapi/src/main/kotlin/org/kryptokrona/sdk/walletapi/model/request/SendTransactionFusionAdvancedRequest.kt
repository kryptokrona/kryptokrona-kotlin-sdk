package org.kryptokrona.sdk.walletapi.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendTransactionFusionAdvancedRequest(
    var destination: String,
    var mixin: Long,
    var sourceAddresses: List<String>,
)
