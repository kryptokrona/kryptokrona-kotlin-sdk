package org.kryptokrona.sdk.walletapi.model.request

import kotlinx.serialization.Serializable

/**
 * Import view only address request
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 */
@Serializable
data class ImportViewOnlyAddressRequest(
    var scanHeight: Long,
    var publicSpendKey: String
)
