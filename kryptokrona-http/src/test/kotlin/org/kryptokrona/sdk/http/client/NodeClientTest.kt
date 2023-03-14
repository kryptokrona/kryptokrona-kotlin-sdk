package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.core.node.Node

class NodeClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val falseNode = Node("test.mjovanc.com", 11898, false)

    private val client = NodeClient(node)

    private val client2 = NodeClient(falseNode)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can reach node` () = runTest {
        val data = client.isNodeRunning()
        assertTrue(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can not reach node` () = runTest {
        val data = client2.isNodeRunning()
        assertFalse(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node info` () = runTest {
        val data = client.getNodeInfo()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node height` () = runTest {
        val data = client.getNodeHeight()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node peers` () = runTest {
        val data = client.getNodePeers()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node fee` () = runTest {
        val data = client.getNodeFee()
        assertNotNull(data)
    }

}