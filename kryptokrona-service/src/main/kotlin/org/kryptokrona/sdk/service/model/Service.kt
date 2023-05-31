package org.kryptokrona.sdk.service.model

import kotlinx.serialization.Serializable

/**
 * Service is a data class that holds information about a Kryptokrona Service.
 * The properties are mutable so that they can be changed during runtime.
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param hostName The host name of the service.
 * @param port The port of the service.
 * @param ssl Whether the service is using SSL.
 */
@Serializable
data class Service(
    var hostName: String,
    var port: Int,
    var fileName: String,
    var password: String,
    var ssl: Boolean
)
