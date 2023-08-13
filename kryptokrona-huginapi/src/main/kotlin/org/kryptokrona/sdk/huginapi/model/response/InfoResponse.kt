package org.kryptokrona.sdk.huginapi.model.response

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val donationAddress: String,
    val status: String
)
