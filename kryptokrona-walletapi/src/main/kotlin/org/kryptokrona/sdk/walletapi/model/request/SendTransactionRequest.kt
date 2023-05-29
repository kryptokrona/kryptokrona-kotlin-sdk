package org.kryptokrona.sdk.walletapi.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendTransactionRequest(
    var destination: String,
    var amount: Long,
    var paymentID: String,
)