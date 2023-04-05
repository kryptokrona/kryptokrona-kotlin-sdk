package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.util.node.Node

class WalletClientTest {

    private val node = Node("techy.ddns.net", 11898, false)

    private val client = WalletClient(node)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data without request parameters` () = runTest {
        val requestData = WalletSyncDataRequest()
        val data = client.getWalletSyncData(requestData)
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data with request body` () = runTest {
        var requestData = WalletSyncDataRequest(startHeight = 10000)
        var data = client.getWalletSyncData(requestData)
        assertNotNull(data)

        requestData = WalletSyncDataRequest(startHeight = 100000, startTimestamp = 50, blockCount = 500)
        data = client.getWalletSyncData(requestData)
        assertNotNull(data)

        requestData = WalletSyncDataRequest(startHeight = 10000, startTimestamp = 100000, blockCount = 50)
        data = client.getWalletSyncData(requestData)
        assertNotNull(data)
    }
}