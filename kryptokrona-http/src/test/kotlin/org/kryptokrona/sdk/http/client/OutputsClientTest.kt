package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.model.node.Node

/**
 * Tests for the OutputsClient class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class OutputsClientTest {

    private val nodeHTTP = Node("privacymine.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = OutputsClient(nodeHTTP)

    private val clientHTTPS = OutputsClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get random outs` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getRandomOuts()
        val dataHTTPS = clientHTTPS.getRandomOuts()

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }
}
