package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.util.node.Node

class WalletClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = WalletClient(nodeHTTP)

    private val clientHTTPS = WalletClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data without request parameters` () = runTest {
        // Arrange
        val requestData = WalletSyncDataRequest()

        // Act
        val dataHTTP = clientHTTP.getWalletSyncData(requestData)
        val dataHTTPS = clientHTTPS.getWalletSyncData(requestData)

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with request body` () = runTest {
        // Arrange
        val requestData1 = WalletSyncDataRequest(startHeight = 10000)
        val requestData2 = WalletSyncDataRequest(startHeight = 100000, startTimestamp = 50, blockCount = 500)
        val requestData3 = WalletSyncDataRequest(startHeight = 10000, startTimestamp = 100000, blockCount = 50)

        // Act
        val dataHTTP1 = clientHTTP.getWalletSyncData(requestData1)
        val dataHTTP2 = clientHTTP.getWalletSyncData(requestData2)
        val dataHTTP3 = clientHTTP.getWalletSyncData(requestData3)

        val dataHTTPS1 = clientHTTPS.getWalletSyncData(requestData1)
        val dataHTTPS2 = clientHTTPS.getWalletSyncData(requestData2)
        val dataHTTPS3 = clientHTTPS.getWalletSyncData(requestData3)

        // Assert
        assertNotNull(dataHTTP1)
        assertNotNull(dataHTTP2)
        assertNotNull(dataHTTP3)

        assertNotNull(dataHTTPS1)
        assertNotNull(dataHTTPS2)
        assertNotNull(dataHTTPS3)
    }
}