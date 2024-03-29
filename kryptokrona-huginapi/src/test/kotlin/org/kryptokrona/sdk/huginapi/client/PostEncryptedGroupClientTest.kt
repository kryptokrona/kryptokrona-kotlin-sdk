package org.kryptokrona.sdk.huginapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.huginapi.model.HuginAPI
import kotlin.test.assertNotNull

class PostEncryptedGroupClientTest {

    private val huginAPI = HuginAPI("localhost", 3000, false)

    private val client = PostEncryptedGroupClient(huginAPI)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all encrypted group posts`() = runTest {
        // Arrange

        // Act
        val encryptedPosts = client.getAll()

        // Assert
        assertNotNull(encryptedPosts)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get encrypted group post by tx hash`() = runTest {
        // Arrange
        val txHash = ""

        // Act
        val encryptedPostByTxhash = client.getByTxHash(txHash)

        // Assert
        assertNotNull(encryptedPostByTxhash)
    }
}