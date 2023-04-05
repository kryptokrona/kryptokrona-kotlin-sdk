package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.node.Node

class BlockClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = BlockClient(nodeHTTP)

    private val clientHTTPS = BlockClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get blocks`() = runTest {
        var data = clientHTTP.getBlocks()
        assertNotNull(data)

        data = clientHTTPS.getBlocks()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get query blocks`() = runTest {
        var data = clientHTTP.getQueryBlocks()
        assertNotNull(data)

        data = clientHTTPS.getQueryBlocks()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get query blocks lite`() = runTest {
        var data = clientHTTP.getQueryBlocksLite()
        assertNotNull(data)

        data = clientHTTPS.getQueryBlocksLite()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get block details by height`() = runTest {
        var data = clientHTTP.getQueryBlocksLite()
        assertNotNull(data)

        data = clientHTTPS.getQueryBlocksLite()
        assertNotNull(data)
    }
}