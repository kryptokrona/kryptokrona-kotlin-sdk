package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.kryptokrona.sdk.util.node.Node

class WalletClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val client = WalletClient(node)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet sync data` () = runTest {
        val data = client.getWalletSyncData()
        assertNotNull(data)
    }
}