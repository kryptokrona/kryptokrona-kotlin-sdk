package org.kryptokrona.sdk.walletapi.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ImportAddressResponse(
    var address: String,
)
