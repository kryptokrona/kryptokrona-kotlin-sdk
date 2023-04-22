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

        assertEquals("OK", dataHTTP1?.status)
        assertEquals("OK", dataHTTP2?.status)
        assertEquals("OK", dataHTTP3?.status)

        assertEquals("OK", dataHTTPS1?.status)
        assertEquals("OK", dataHTTPS2?.status)
        assertEquals("OK", dataHTTPS3?.status)
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
        val blockWithHashes1 = BlocksDetailsByHashesRequest(
            blockHashes = listOf(
                "88a4d0edfb6b640bda0d97f45668869fc3fe76dd963946b0a265f135105e7a3d",
                "57a839bba17c4b903cd61cde342bbbbcc38826cea9446c8d76582f3489c00e66",
                "680a09b5f3ea1c37d1fff36400fc392db0ba0c17bcc0c5225376260d898bfc11"
            ))
        val blockWithHashes2 = BlocksDetailsByHashesRequest(
            blockHashes = listOf(
                "622a5f45a1aa5873ec9ae381d68690abd17e097d92e9fea83c8a7177e8af86c4",
                "1b9462b655eecfffc01c65559edcdaac927277c6301a963461902ef88b7bf3c2",
                "50fe909fd687aadbe03e3e09f05adfda689930ccfc1cc736b37fb3cb3555d6fc",
                "70ad6b86ee32fb609a54935c9af854068fbfe3c3e2567eb9d426fad022fc79d9",
                "003e78f1bd43841279c24bca5c6e7bfa740c29d20b91f269c9bf11976e339095",
                "001e9c66daf6f48c42bc55852cef2915255c4f2b401ea5f27478e4985d720630"
            ))
        val blockWithHashes3 = BlocksDetailsByHashesRequest(
            blockHashes = listOf(
                "d42be60ce5297cc0da3e73221ad5d2c9ef80dc0822d56a74fd8711fe4b0252e3",
                "708f3e0d06e0994cfbe1f7575d1ff5c346ff3e53afe04e114e293c462a465f07",
                "a77a8a07f492d1e598b6ee45451bf9ddeeeeff97f96bc991e0b2f3f56d8f3249",
                "c7fe22599375aa522f8c9de2b407d2d41597799a2c8a1e112498d0f1ac795033",
                "8ce9af8c7e3a32bef4e03e5905eed74c72b56e01f9197e1fa61bd980ee1fc6fb",
                "15e42efcea52a31d92997a2dd36e57b34dc833029572be4009168d68d030c40b",
                "ba5394f629fb6aeb244b1961b5e024bea8a1b7905358ca9c1ca6646e8d183915",
                "d566ca75c7ce695d1e96b76a113ac3d50acd40481db447c53c797b862e0c43a1"
            ))

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
        val blockTime = 90L
        val blockWithTimestamp1 = BlocksHashesByTimestampsRequest(
            timestampBegin = 1554236111,
            secondsCount = blockTime
        )
        val blockWithTimestamp2 = BlocksHashesByTimestampsRequest(
            timestampBegin = 1554236111,
            secondsCount = blockTime * 10
        )
        val blockWithTimestamp3 = BlocksHashesByTimestampsRequest(
            timestampBegin = 1554236111,
            secondsCount = blockTime * 100
        )

        // Act
        val dataHTTP1 = clientHTTP.getBlocksHashesByTimestamps(blockWithTimestamp1)
        val dataHTTP2 = clientHTTP.getBlocksHashesByTimestamps(blockWithTimestamp2)
        val dataHTTP3 = clientHTTP.getBlocksHashesByTimestamps(blockWithTimestamp3)

        val dataHTTPS1 = clientHTTPS.getBlocksHashesByTimestamps(blockWithTimestamp1)
        val dataHTTPS2 = clientHTTPS.getBlocksHashesByTimestamps(blockWithTimestamp2)
        val dataHTTPS3 = clientHTTPS.getBlocksHashesByTimestamps(blockWithTimestamp3)

        // Assert
        assertEquals("OK", dataHTTP1?.status)
        assertEquals("OK", dataHTTP2?.status)
        assertEquals("OK", dataHTTP3?.status)

        assertEquals("OK", dataHTTPS1?.status)
        assertEquals("OK", dataHTTPS2?.status)
        assertEquals("OK", dataHTTPS3?.status)
    }
}
