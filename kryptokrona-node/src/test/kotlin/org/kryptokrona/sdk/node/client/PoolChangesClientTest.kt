package org.kryptokrona.sdk.node.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.node.model.request.PoolChangesLiteRequest
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
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite with tailBlockId`() = runTest {
        // Arrange
        val poolChangesLiteRequest = PoolChangesLiteRequest(tailBlockId = "")

        // Act
        val dataHTTP = clientHTTP.getPoolChangesLite(poolChangesLiteRequest)
        val dataHTTPS = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite with tailBlockId and knownTxsIds`() = runTest {
        // Arrange
        val knownTxs1 = mutableListOf<String>()
        val knownTxs2 = mutableListOf<String>()

        val poolChangesLiteRequest = PoolChangesLiteRequest()

        val dataHTTP1 = clientHTTP.getPoolChangesLite(poolChangesLiteRequest)
        val dataHTTPS1 = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest)
        val addedTxs1 = dataHTTP1.let { it?.addedTxs }
        val addedTxs2 = dataHTTPS1.let { it?.addedTxs }

        addedTxs1?.forEach { knownTxs1.add(it.transactionHash) }
        addedTxs2?.forEach { knownTxs2.add(it.transactionHash) }

        val poolChangesLiteRequest1 = PoolChangesLiteRequest(tailBlockId = "", knownTxsIds = knownTxs1)
        val poolChangesLiteRequest2 = PoolChangesLiteRequest(tailBlockId = "", knownTxsIds = knownTxs2)

        // Act
        val dataHTTP2 = clientHTTP.getPoolChangesLite(poolChangesLiteRequest1)
        val dataHTTPS2 = clientHTTPS.getPoolChangesLite(poolChangesLiteRequest2)

        val newKnownTxs1 = mutableListOf<String>()
        val newKnownTxs2 = mutableListOf<String>()

        dataHTTP2.let { response -> response?.addedTxs?.forEach { newKnownTxs1.add(it.transactionHash) } }
        dataHTTPS2.let { response -> response?.addedTxs?.forEach { newKnownTxs2.add(it.transactionHash) } }

        val allKnownTxNotInPool1 = knownTxs1.all { it !in newKnownTxs1 }
        val allKnownTxNotInPool2 = knownTxs2.all { it !in newKnownTxs2 }

        // Assert
        if (dataHTTP1 != null) assertEquals("OK", dataHTTP1.status)
        if (dataHTTP2 != null) assertEquals("OK", dataHTTP2.status)
        if (dataHTTPS1 != null) assertEquals("OK", dataHTTPS1.status)
        if (dataHTTPS2 != null) assertEquals("OK", dataHTTPS2.status)

        assertTrue { allKnownTxNotInPool1 }
        assertTrue { allKnownTxNotInPool2 }
    }
}
