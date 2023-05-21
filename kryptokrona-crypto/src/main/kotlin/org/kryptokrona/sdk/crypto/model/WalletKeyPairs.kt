package org.kryptokrona.sdk.crypto.model

/**
 * Wallet Key pairs model.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
data class WalletKeyPairs(
    val publicSpendKey: String,
    val privateSpendKey: String,
    val publicViewKey: String,
    val privateViewKey: String
)
