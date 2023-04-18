package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.model.node.Node

/**
 * Tests for the PoolChangesClient class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class PoolChangesClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = PoolChangesClient(nodeHTTP)

    private val clientHTTPS = PoolChangesClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite()
        val dataHTTPS = clientHTTPS.getPoolChangesLite()

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }
}
