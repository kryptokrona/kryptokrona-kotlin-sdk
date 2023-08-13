package org.kryptokrona.sdk.huginapi.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(
    val payload: String
)
