package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.crypto.util.convertHexToBytes
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the Crypto class with the external C library
 * so that everything loads correctly.
 *
 * The tests are not exhaustive, but they are enough to make sure that the library is loaded correctly.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class CryptoTest {

    private val crypto = Crypto()

    @Test
    fun `can generate key derivation`() {
        // Arrange
        val privateViewKey = "548b044902b6da1edf7abbba8de293cc2c8b3db901864027d35d1587f003e50f"
        val privView = convertHexToBytes(privateViewKey)
        val txPubKey = convertHexToBytes("fdfd97d2ea9f1c25df773ff2c973d885653a3ee643157eb0ae2b6dd98f0b6984")
        val derivation = ByteArray(32)

        // Act
        crypto.generateKeyDerivation(txPubKey, privView, derivation)

        // Assert
        assertEquals(32, derivation.size)
        assertTrue { derivation.isNotEmpty() }
    }

    @Test
    fun `can underive public key`() {
        // Arrange
        val privateViewKey = "548b044902b6da1edf7abbba8de293cc2c8b3db901864027d35d1587f003e50f"

        val derivation = ByteArray(32)
        val index = 0L
        val derivedKey = convertHexToBytes("55e99da42788f95d2415bf51039517dd129cfb4a1b66d676977341ce5a92280d")
        val base = ByteArray(32)

        val privView = convertHexToBytes(privateViewKey)
        val txPubKey = convertHexToBytes("fdfd97d2ea9f1c25df773ff2c973d885653a3ee643157eb0ae2b6dd98f0b6984")

        // Act
        crypto.generateKeyDerivation(txPubKey, privView, derivation)
        crypto.underivePublicKey(derivation, index, derivedKey, base)

        // Assert
        assertEquals(32, base.size)
        assertTrue { base.isNotEmpty() }
    }

    @Test
    fun `can generate key image`() {
        // Arrange
        val privateViewKey = "548b044902b6da1edf7abbba8de293cc2c8b3db901864027d35d1587f003e50f"
        val privView = convertHexToBytes(privateViewKey)
        val txPubKey = convertHexToBytes("fdfd97d2ea9f1c25df773ff2c973d885653a3ee643157eb0ae2b6dd98f0b6984")
        val derivation = ByteArray(32)
        val image = ByteArray(32)

        // Act
        crypto.generateKeyDerivation(txPubKey, privView, derivation)
        crypto.generateKeyImage(txPubKey, privView, image)

        // Assert
        assertEquals(32, image.size)
        assertTrue { image.isNotEmpty() }
    }

    @Test
    fun `can derive public key`() {
        // Arrange
        val derivation = ByteArray(32)
        val index = 0L
        val derivedKey = convertHexToBytes("55e99da42788f95d2415bf51039517dd129cfb4a1b66d676977341ce5a92280d")
        val base = ByteArray(32)

        // Act
        crypto.derivePublicKey(derivation, index, derivedKey, base)

        // Assert
        assertEquals(32, base.size)
        assertTrue { base.isNotEmpty() }
    }

    @Test
    fun `can derive secret key`() {
        // Arrange
        val derivation = ByteArray(32)
        val index = 0L
        val derivedKey = convertHexToBytes("55e99da42788f95d2415bf51039517dd129cfb4a1b66d676977341ce5a92280d")
        val base = ByteArray(32)

        // Act
        crypto.deriveSecretKey(derivation, index, derivedKey, base)

        // Assert
        assertEquals(32, base.size)
        assertTrue { base.isNotEmpty() }
    }
}