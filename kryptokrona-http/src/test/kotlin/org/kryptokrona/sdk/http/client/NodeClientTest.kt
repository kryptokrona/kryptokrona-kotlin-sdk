package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.model.node.Node

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
        // Arrange

        // Act
        val dataHTTP = clientHTTP.isNodeRunning()
        val dataHTTPS = clientHTTPS.isNodeRunning()

        // Assert
        assertTrue(dataHTTP)
        assertTrue(dataHTTPS)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can not reach node` () = runTest {
        // Arrange

        // Act
        val dataHTTP2 = clientHTTP2.isNodeRunning()

        // Assert
        assertFalse(dataHTTP2)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node info` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getNodeInfo()
        val dataHTTPS = clientHTTPS.getNodeInfo()

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node height` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getNodeHeight()
        val dataHTTPS = clientHTTPS.getNodeHeight()

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node peers` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getNodePeers()
        val dataHTTPS = clientHTTPS.getNodePeers()

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node fee` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getNodeFee()
        val dataHTTPS = clientHTTPS.getNodeFee()

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

}