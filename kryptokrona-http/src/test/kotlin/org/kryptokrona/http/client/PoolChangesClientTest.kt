package org.kryptokrona.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.kryptokrona.core.node.Node

class PoolChangesClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val client = PoolChangesClient(node)

    @Test
    fun `can get pool changes lite` () = runTest {
        val data = client.getPoolChangesLite()
        assertNotNull(data)
    }

}