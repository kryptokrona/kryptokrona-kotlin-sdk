package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.core.node.Node

class IndexesClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val client = IndexesClient(node)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get O Indexes` () = runTest {
        val data = client.getOIndexes()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get global indexes for range` () = runTest {
        val data = client.getGlobalIndexesForRange()
        assertNotNull(data)
    }

}