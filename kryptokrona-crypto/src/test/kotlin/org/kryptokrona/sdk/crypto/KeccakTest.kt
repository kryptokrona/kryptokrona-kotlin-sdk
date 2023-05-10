package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.crypto.util.toHex
import kotlin.test.assertEquals

/**
 * Tests for the Keccak class with the external C library
 * so that everything loads correctly.
 *
 * The tests are not exhaustive, but they are enough to make sure that the library is loaded correctly.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class KeccakTest {

    private val keccak = Keccak()

    @Test
    fun `can compute hash value of an input message`() {
        // Arrange
        val input = "hello, world".toByteArray(Charsets.UTF_8)
        val mdLength = 32
        val md = ByteArray(mdLength)

        // Act
        keccak.computeHashValue(input, input.size, md, mdLength)
        val output = toHex(md)

        // Assert
        val expectedOutput = "29bf7021020ea89dbd91ef52022b5a654b55ed418c9e7aba71ef3b43a51669f2"
        assertEquals(expectedOutput, output)
    }
}
