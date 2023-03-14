package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.core.node.Node

class PoolChangesClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val client = PoolChangesClient(node)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get pool changes lite` () = runTest {
        val data = client.getPoolChangesLite()
        assertNotNull(data)
    }

}