package org.kryptokrona.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.core.node.Node

class OutputsClientTest {

    private val node = Node("privacymine.net", 11898, false)

    private val client = OutputsClient(node)

    @Test
    fun `can get random outs` () = runTest {
        val data = client.getRandomOuts()
        assertNotNull(data)
    }

}