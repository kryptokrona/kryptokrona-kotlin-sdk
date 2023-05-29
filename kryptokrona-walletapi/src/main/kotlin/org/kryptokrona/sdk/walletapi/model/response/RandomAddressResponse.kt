package org.kryptokrona.sdk.walletapi.model.response

import kotlinx.serialization.Serializable

@Serializable
data class RandomAddressResponse(
    var address: String,
    var privateSpendKey: String,
)
