package org.kryptokrona.sdk.node.common

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val MAX_SEND_COUNT = 50

/**
 * HTTP client singleton object.
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
object HttpClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
        install(HttpSend) {
            maxSendCount = MAX_SEND_COUNT
        }
    }
}

