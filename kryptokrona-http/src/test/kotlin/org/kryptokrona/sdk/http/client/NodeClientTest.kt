package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.node.Node

class NodeClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val falseNodeHTTP = Node("test.mjovanc.com", 11898, false)

    private val clientHTTP = NodeClient(nodeHTTP)

    private val clientHTTPS = NodeClient(nodeHTTPS)

    private val clientHTTP2 = NodeClient(falseNodeHTTP)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can reach node` () = runTest {
        var data = clientHTTP.isNodeRunning()
        assertTrue(data)

        data = clientHTTPS.isNodeRunning()
        assertTrue(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can not reach node` () = runTest {
        val data = clientHTTP2.isNodeRunning()
        assertFalse(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node info` () = runTest {
        var data = clientHTTP.getNodeInfo()
        assertNotNull(data)

        data = clientHTTPS.getNodeInfo()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node height` () = runTest {
        var data = clientHTTP.getNodeHeight()
        assertNotNull(data)

        data = clientHTTPS.getNodeHeight()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node peers` () = runTest {
        var data = clientHTTP.getNodePeers()
        assertNotNull(data)

        data = clientHTTPS.getNodePeers()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node fee` () = runTest {
        var data = clientHTTP.getNodeFee()
        assertNotNull(data)

        data = clientHTTPS.getNodeFee()
        assertNotNull(data)
    }

}