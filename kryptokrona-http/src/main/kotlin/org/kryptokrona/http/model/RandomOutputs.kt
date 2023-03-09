package org.kryptokrona.http.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomOutputs(val outputs: List<Int>, val status: String)
