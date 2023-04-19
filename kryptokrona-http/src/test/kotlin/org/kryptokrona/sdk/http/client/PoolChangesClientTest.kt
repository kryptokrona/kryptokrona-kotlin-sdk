package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.PoolChangesLiteRequest
import org.kryptokrona.sdk.util.model.node.Node

/**
 * Tests for the PoolChangesClient class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class PoolChangesClientTest {

    private val nodeHTTP = Node("privacymine.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = PoolChangesClient(nodeHTTP)

    private val clientHTTPS = PoolChangesClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite` () = runTest {
        // Arrange
        val poolChangesLiteRequest1 = PoolChangesLiteRequest()
        val poolChangesLiteRequest2 = PoolChangesLiteRequest(1)
        val poolChangesLiteRequest3 = PoolChangesLiteRequest(1, listOf("transactionId1", "transactionId2"))

        // Act
        val dataHTTP1 = clientHTTP.getPoolChangesLite(poolChangesLiteRequest1)
        val dataHTTP2 = clientHTTP.getPoolChangesLite(poolChangesLiteRequest2)
        val dataHTTP3 = clientHTTP.getPoolChangesLite(poolChangesLiteRequest3)

        val dataHTTPS1 = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest1)
        val dataHTTPS2 = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest2)
        val dataHTTPS3 = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest3)

        // Assert
        if (dataHTTP1 != null) assertEquals(dataHTTP1.status, "OK")
        if (dataHTTP2 != null) assertEquals(dataHTTP2.status, "OK")
        if (dataHTTP3 != null) assertEquals(dataHTTP3.status, "OK")

        if (dataHTTPS1 != null) assertEquals(dataHTTPS1.status, "OK")
        if (dataHTTPS2 != null) assertEquals(dataHTTPS2.status, "OK")
        if (dataHTTPS3 != null) assertEquals(dataHTTPS3.status, "OK")
    }
}
