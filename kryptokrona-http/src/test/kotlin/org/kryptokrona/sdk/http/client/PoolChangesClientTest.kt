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
    fun `can get pool changes lite without body` () = runTest {
        // Arrange
        val poolChangesLiteRequest = PoolChangesLiteRequest()

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite(poolChangesLiteRequest)
        val dataHTTPS = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite with tailBlockId`() = runTest {
        // Arrange
        val poolChangesLiteRequest = PoolChangesLiteRequest(1)

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite(poolChangesLiteRequest)
        val dataHTTPS = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite with tailBlockId and knownTxIds`() = runTest {
        // Arrange
        val poolChangesLiteRequest3 = PoolChangesLiteRequest(1, listOf("transactionId1", "transactionId2"))

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite(poolChangesLiteRequest3)
        val dataHTTPS = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest3)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite with tailBlockId and knownTxIds and addedTxs`() = runTest {
        // Arrange
        val poolChangesLiteRequest = PoolChangesLiteRequest(
            1,
            listOf("knownTx1", "knownTx2"),
            listOf("addedTx1", "addedTx2")
        )

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite(poolChangesLiteRequest)
        val dataHTTPS = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite with tailBlockId and knownTxIds and addedTxs and deletedTxs`() = runTest {
        // Arrange
        val poolChangesLiteRequest = PoolChangesLiteRequest(
            1,
            listOf("knownTx1", "knownTx2"),
            listOf("addedTx1", "addedTx2"),
            listOf("deletedTx1", "deletedTx2")
        )

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite(poolChangesLiteRequest)
        val dataHTTPS = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }
}
