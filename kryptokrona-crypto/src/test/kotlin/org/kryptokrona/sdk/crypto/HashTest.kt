package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

/**
 * Tests for the Hash class with the external C library
 * so that everything loads correctly.
 *
 * The tests are not exhaustive, but they are enough to make sure that the library is loaded correctly.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class HashTest {

    private val hash = Hash()

    @Test
    fun `can generate cn fast hash`() {
        // Arrange
        val data = "Hello, World!".toByteArray()
        val expectedHash = byteArrayOf(
            // Fill this array with the expected hash value
        )
        val actualHash = ByteArray(expectedHash.size)

        // Act
        hash.cnFastHash(data, data.size, actualHash)

        // Assert
        Assertions.assertArrayEquals(expectedHash, actualHash)
        assertTrue { actualHash.isNotEmpty() }
        assertTrue { actualHash.any { it.toInt() != 0 } }
    }
}
