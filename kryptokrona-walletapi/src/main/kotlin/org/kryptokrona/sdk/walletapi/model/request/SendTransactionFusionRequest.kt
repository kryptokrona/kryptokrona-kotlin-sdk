package org.kryptokrona.sdk.walletapi.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendTransactionFusionRequest(
    var test: String,
)
