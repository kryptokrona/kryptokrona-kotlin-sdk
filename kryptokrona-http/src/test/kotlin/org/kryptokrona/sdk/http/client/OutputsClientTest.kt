package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.node.Node

class OutputsClientTest {

    private val node = Node("techy.ddns.net", 11898, false)

    private val client = OutputsClient(node)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get random outs` () = runTest {
        val data = client.getRandomOuts()
        assertNotNull(data)
    }

}