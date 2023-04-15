package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.crypto.util.convertHexToBytes

/**
 * Tests for the Crypto class with the external C library
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class CryptoTest {

    private val crypto = Crypto()

    @Test
    fun `can generate key derivation`() {
        val publicSpendKey = "cde60afedba1e88a9c7e8b28cc038ee018d5a24a1a239cdcb8d32506a594f3cb"
        val privateViewKey = "8f066e33d45a0205b772f47b5a5d66f5b5e08fc329c45fc5f2a15a998ad0d4b4"

        val pubSpend = convertHexToBytes(publicSpendKey)
        val privView = convertHexToBytes(privateViewKey)
        val txPubKey = convertHexToBytes("dsadadadasdas")

        val test = ByteArray(32)
        val derivation = crypto.generateKeyDerivation(txPubKey, privView, test)
        println(derivation)
    }
}