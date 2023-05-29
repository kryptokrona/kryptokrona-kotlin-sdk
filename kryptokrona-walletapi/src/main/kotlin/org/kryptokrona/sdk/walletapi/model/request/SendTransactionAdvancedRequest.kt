package org.kryptokrona.sdk.walletapi.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendTransactionAdvancedRequest(
    var destination: String,
    var amount: Long,
    var paymentID: String,
    var mixin: Long,
    var fee: Long,
    var sourceAddress: String,
    var changeAddress: String,
    var unlockTime: Long,
)
