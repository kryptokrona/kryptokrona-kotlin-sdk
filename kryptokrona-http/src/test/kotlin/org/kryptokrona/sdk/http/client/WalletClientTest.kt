package org.kryptokrona.sdk.http.client

import io.ktor.client.network.sockets.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.util.model.node.Node

/**
 * Tests for the WalletClient class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class WalletClientTest {

    private val nodeHTTP = Node("privacymine.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = WalletClient(nodeHTTP)

    private val clientHTTPS = WalletClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data without body` () = runTest {
        // Arrange
        val requestData = WalletSyncDataRequest()

        // Act
        val dataHTTP = clientHTTP.getWalletSyncData(requestData)
        val dataHTTPS = clientHTTPS.getWalletSyncData(requestData)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with start height` () = runTest {
        // Arrange
        val requestData = WalletSyncDataRequest(startHeight = 10000)

        // Act
        val dataHTTP = clientHTTP.getWalletSyncData(requestData)
        val dataHTTPS = clientHTTPS.getWalletSyncData(requestData)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)

        dataHTTP?.items?.get(0)?.let { assertEquals(it.blockHeight, 10000) }
        dataHTTPS?.items?.get(0)?.let { assertEquals(it.blockHeight, 10000) }

        assertEquals(100, dataHTTP?.items?.size)
        assertEquals(100, dataHTTPS?.items?.size)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with start height and start timestamp`() = runTest {
        // Arrange
        val requestData = WalletSyncDataRequest(
            startHeight = 100000,
            startTimestamp = 50
        )

        // Act
        val dataHTTP = clientHTTP.getWalletSyncData(requestData)
        val dataHTTPS = clientHTTPS.getWalletSyncData(requestData)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with start height, start timestamp and block count`() = runTest {
        // Arrange
        val requestData = WalletSyncDataRequest(
            startHeight = 10000,
            startTimestamp = 100000,
            blockCount = 50
        )

        // Act
        val dataHTTP = clientHTTP.getWalletSyncData(requestData)
        val dataHTTPS = clientHTTPS.getWalletSyncData(requestData)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }
}
