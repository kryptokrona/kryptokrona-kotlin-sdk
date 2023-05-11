package org.kryptokrona.sdk.crypto.model

/**
 * Wallet Key pairs model.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
data class WalletKeyPairs(
    val publicKey: String,
    val privateKey: String,
    val publicSpendKey: String,
    val privateSpendKey: String
)
