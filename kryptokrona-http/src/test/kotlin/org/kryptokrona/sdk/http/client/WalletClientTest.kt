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
    fun `can get wallet sync data` () = runTest {
        val requestData = WalletSyncDataRequest(null, null, null, null, null)
        val data = client.getWalletSyncData(requestData)
        assertNotNull(data)
    }
}