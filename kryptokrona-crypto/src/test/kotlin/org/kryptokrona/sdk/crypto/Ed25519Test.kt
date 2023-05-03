package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the Ed25519 class with the external C library
 * so that everything loads correctly.
 *
 * The tests are not exhaustive, but they are enough to make sure that the library is loaded correctly.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class Ed25519Test {

    private val ed25519 = Ed25519()

    @Test
    fun `can create key pair`() {
        // Arrange
        val publicKey = ByteArray(32)
        val privateKey = ByteArray(64)
        val seed = ByteArray(32)

        // Act
        ed25519.createKeyPair(publicKey, privateKey, seed)

        // Assert
        assertEquals(32, publicKey.size)
        assertEquals(64, privateKey.size)
        assertTrue { publicKey.isNotEmpty() }
        assertTrue { privateKey.isNotEmpty() }
    }
}
