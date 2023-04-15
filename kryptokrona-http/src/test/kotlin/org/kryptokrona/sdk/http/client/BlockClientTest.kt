package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksRequest
import org.kryptokrona.sdk.util.model.node.Node
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the BlockClient class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class BlockClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = BlockClient(nodeHTTP)

    private val clientHTTPS = BlockClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get block details by height`() = runTest {
        // Arrange
        val blockWithHeight0 = BlockDetailsByHeightRequest(0)
        val blockWithHeight1 = BlockDetailsByHeightRequest(1)
        val blockWithHeight100 = BlockDetailsByHeightRequest(100)

        // Act
        val dataHTTP1 = clientHTTP.getBlockDetailsByHeight(blockWithHeight0)
        val dataHTTP2 = clientHTTP.getBlockDetailsByHeight(blockWithHeight1)
        val dataHTTP3 = clientHTTP.getBlockDetailsByHeight(blockWithHeight100)

        val dataHTTPS1 = clientHTTPS.getBlockDetailsByHeight(blockWithHeight0)
        val dataHTTPS2 = clientHTTPS.getBlockDetailsByHeight(blockWithHeight1)
        val dataHTTPS3 = clientHTTPS.getBlockDetailsByHeight(blockWithHeight100)

        // Assert
        assertTrue(dataHTTP1?.block?.index == 0L)
        assertTrue(dataHTTP2?.block?.index == 1L)
        assertTrue(dataHTTP3?.block?.index == 100L)

        assertTrue(dataHTTPS1?.block?.index == 0L)
        assertTrue(dataHTTPS2?.block?.index == 1L)
        assertTrue(dataHTTPS3?.block?.index == 100L)

        assertEquals(dataHTTP1?.status, "OK")
        assertEquals(dataHTTP2?.status, "OK")
        assertEquals(dataHTTP3?.status, "OK")

        assertEquals(dataHTTPS1?.status, "OK")
        assertEquals(dataHTTPS2?.status, "OK")
        assertEquals(dataHTTPS3?.status, "OK")
    }
}