package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.node.Node

class OutputsClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = OutputsClient(nodeHTTP)

    private val clientHTTPS = OutputsClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get random outs` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getRandomOuts()
        val dataHTTPS = clientHTTPS.getRandomOuts()

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

}