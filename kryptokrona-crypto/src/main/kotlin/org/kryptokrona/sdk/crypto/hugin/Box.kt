package org.kryptokrona.sdk.crypto.hugin

/**
 * Simple box object.
 * Will be removed once it will be implemented in SDK.
 */
@Serializable
data class Box(
    val box: String,

    @SerialName("t")
    val timestamp: Long = 0
)
