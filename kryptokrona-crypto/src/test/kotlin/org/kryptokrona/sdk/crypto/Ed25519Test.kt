package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Test

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
        TODO()
    }
}