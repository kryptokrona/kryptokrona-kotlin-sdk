package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.util.node.Node

class WalletClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = WalletClient(nodeHTTP)

    private val clientHTTPS = WalletClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data without request parameters` () = runTest {
        val requestData = WalletSyncDataRequest()

        var data = clientHTTP.getWalletSyncData(requestData)
        assertNotNull(data)

        data = clientHTTPS.getWalletSyncData(requestData)
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with request body` () = runTest {
        var requestData = WalletSyncDataRequest(startHeight = 10000)
        var data = clientHTTP.getWalletSyncData(requestData)
        assertNotNull(data)

        data = clientHTTPS.getWalletSyncData(requestData)
        assertNotNull(data)

        requestData = WalletSyncDataRequest(startHeight = 100000, startTimestamp = 50, blockCount = 500)
        data = clientHTTP.getWalletSyncData(requestData)
        assertNotNull(data)

        data = clientHTTPS.getWalletSyncData(requestData)
        assertNotNull(data)

        requestData = WalletSyncDataRequest(startHeight = 10000, startTimestamp = 100000, blockCount = 50)
        data = clientHTTP.getWalletSyncData(requestData)
        assertNotNull(data)

        data = clientHTTPS.getWalletSyncData(requestData)
        assertNotNull(data)
    }
}