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
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with request body` () = runTest {
        // Arrange
        val requestData1 = WalletSyncDataRequest(startHeight = 10000)
        val requestData2 = WalletSyncDataRequest(startHeight = 100000, startTimestamp = 50, blockCount = 100)
        val requestData3 = WalletSyncDataRequest(startHeight = 10000, startTimestamp = 100000, blockCount = 50)

        // Act
        val dataHTTP1 = clientHTTP.getWalletSyncData(requestData1)
        val dataHTTP2 = clientHTTP.getWalletSyncData(requestData2)
        val dataHTTP3 = clientHTTP.getWalletSyncData(requestData3)

        val dataHTTPS1 = clientHTTPS.getWalletSyncData(requestData1)
        val dataHTTPS2 = clientHTTPS.getWalletSyncData(requestData2)
        val dataHTTPS3 = clientHTTPS.getWalletSyncData(requestData3)

        // Assert
        if (dataHTTP1 != null) assertEquals(dataHTTP1.status, "OK")
        if (dataHTTP2 != null) assertEquals(dataHTTP2.status, "OK")
        if (dataHTTP3 != null) assertEquals(dataHTTP3.status, "OK")
        if (dataHTTPS1 != null) assertEquals(dataHTTPS1.status, "OK")
        if (dataHTTPS2 != null) assertEquals(dataHTTPS2.status, "OK")
        if (dataHTTPS3 != null) assertEquals(dataHTTPS3.status, "OK")

        dataHTTP1?.items?.get(0)?.let { assertEquals(it.blockHeight, 10000) }
        dataHTTP2?.items?.get(0)?.let { assertEquals(it.blockHeight, 100000) }
        dataHTTP3?.items?.get(0)?.let { assertEquals(it.blockHeight, 10000) }
        dataHTTPS1?.items?.get(0)?.let { assertEquals(it.blockHeight, 10000) }
        dataHTTPS2?.items?.get(0)?.let { assertEquals(it.blockHeight, 100000) }
        dataHTTPS3?.items?.get(0)?.let { assertEquals(it.blockHeight, 10000) }

        assertEquals(100, dataHTTP2?.items?.size)
        assertEquals(50, dataHTTP3?.items?.size)
        assertEquals(100, dataHTTPS2?.items?.size)
        assertEquals(50, dataHTTPS3?.items?.size)
    }
}