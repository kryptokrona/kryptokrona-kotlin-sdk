package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.core.node.Node

class BlockClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val client = BlockClient(node)

    @Test
    fun `can get blocks`() = runTest {
        val data = client.getBlocks()
        assertNotNull(data)
    }

    @Test
    fun `can get query blocks`() = runTest {
        val data = client.getQueryBlocks()
        assertNotNull(data)
    }

    @Test
    fun `can get query blocks lite`() = runTest {
        val data = client.getQueryBlocksLite()
        assertNotNull(data)
    }
}