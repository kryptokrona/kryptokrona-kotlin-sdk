package org.kryptokrona.sdk.walletapi.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ImportAddressRequest(
    var scanHeight: Long,
    var privateSpendKey: String,
)
