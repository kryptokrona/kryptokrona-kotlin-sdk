package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksDetailsByHashesRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksDetailsByHeightsRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksHashesByTimestampsRequest
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

    private val nodeHTTP = Node("privacymine.net", 11898, false)

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

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get blocks details by heights`() = runTest {
        // Arrange
        val blocksWithHeights1 = BlocksDetailsByHeightsRequest(listOf(1, 2, 3))
        val blocksWithHeights100 = BlocksDetailsByHeightsRequest(listOf(100, 101, 102, 103, 104, 105))
        val blocksWithHeights1000 = BlocksDetailsByHeightsRequest(
            listOf(1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007)
        )

        // Act
        val dataHTTP1 = clientHTTP.getBlocksDetailsByHeights(blocksWithHeights1)
        val dataHTTP2 = clientHTTP.getBlocksDetailsByHeights(blocksWithHeights100)
        val dataHTTP3 = clientHTTP.getBlocksDetailsByHeights(blocksWithHeights1000)

        val dataHTTPS1 = clientHTTPS.getBlocksDetailsByHeights(blocksWithHeights1)
        val dataHTTPS2 = clientHTTPS.getBlocksDetailsByHeights(blocksWithHeights100)
        val dataHTTPS3 = clientHTTPS.getBlocksDetailsByHeights(blocksWithHeights1000)

        // Assert
        assertTrue(dataHTTP1?.blocks?.size == 3)
        assertTrue(dataHTTP2?.blocks?.size == 6)
        assertTrue(dataHTTP3?.blocks?.size == 8)

        assertTrue(dataHTTPS1?.blocks?.size == 3)
        assertTrue(dataHTTPS2?.blocks?.size == 6)
        assertTrue(dataHTTPS3?.blocks?.size == 8)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get blocks details by hashes`() = runTest {
        // Arrange
        val blockWithHashes1 = BlocksDetailsByHashesRequest(listOf("hash1", "hash2", "hash3"))
        val blockWithHashes2 = BlocksDetailsByHashesRequest(
            listOf("hash4", "hash5", "hash6", "hash7", "hash8", "hash9")
        )
        val blockWithHashes3 = BlocksDetailsByHashesRequest(
            listOf("hash10", "hash11", "hash12", "hash13", "hash14", "hash15", "hash16", "hash17")
        )

        // Act
        val dataHTTP1 = clientHTTP.getBlocksDetailsByHashes(blockWithHashes1)
        val dataHTTP2 = clientHTTP.getBlocksDetailsByHashes(blockWithHashes2)
        val dataHTTP3 = clientHTTP.getBlocksDetailsByHashes(blockWithHashes3)

        val dataHTTPS1 = clientHTTPS.getBlocksDetailsByHashes(blockWithHashes1)
        val dataHTTPS2 = clientHTTPS.getBlocksDetailsByHashes(blockWithHashes2)
        val dataHTTPS3 = clientHTTPS.getBlocksDetailsByHashes(blockWithHashes3)

        // Assert
        assertTrue(dataHTTP1?.blocks?.size == 3)
        assertTrue(dataHTTP2?.blocks?.size == 6)
        assertTrue(dataHTTP3?.blocks?.size == 8)

        assertTrue(dataHTTPS1?.blocks?.size == 3)
        assertTrue(dataHTTPS2?.blocks?.size == 6)
        assertTrue(dataHTTPS3?.blocks?.size == 8)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get blocks hashes by timestamps`() = runTest {
        // Arrange
        val blockWithTimestamp1 = BlocksHashesByTimestampsRequest(listOf(1554236111))
        val blockWithTimestamp2 = BlocksHashesByTimestampsRequest(listOf(1554236111, 1554236112))
        val blockWithTimestamp3 = BlocksHashesByTimestampsRequest(listOf(1554236111, 1554236112, 1554236113))

        // Act
        val dataHTTP1 = clientHTTP.getBlocksHashesByTimestamps(blockWithTimestamp1)
        val dataHTTP2 = clientHTTP.getBlocksHashesByTimestamps(blockWithTimestamp2)
        val dataHTTP3 = clientHTTP.getBlocksHashesByTimestamps(blockWithTimestamp3)

        val dataHTTPS1 = clientHTTPS.getBlocksHashesByTimestamps(blockWithTimestamp1)
        val dataHTTPS2 = clientHTTPS.getBlocksHashesByTimestamps(blockWithTimestamp2)
        val dataHTTPS3 = clientHTTPS.getBlocksHashesByTimestamps(blockWithTimestamp3)

        // Assert
        assertTrue(dataHTTP1?.blockHashes?.size == 3)
        assertTrue(dataHTTP2?.blockHashes?.size == 6)
        assertTrue(dataHTTP3?.blockHashes?.size == 9)

        assertTrue(dataHTTPS1?.blockHashes?.size == 3)
        assertTrue(dataHTTPS2?.blockHashes?.size == 6)
        assertTrue(dataHTTPS3?.blockHashes?.size == 9)
    }
}
