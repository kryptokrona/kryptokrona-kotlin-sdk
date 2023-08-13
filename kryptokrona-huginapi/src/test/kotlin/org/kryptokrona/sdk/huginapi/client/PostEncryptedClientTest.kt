package org.kryptokrona.sdk.huginapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.huginapi.model.HuginAPI
import kotlin.test.assertNotNull

class PostEncryptedClientTest {

    private val huginAPI = HuginAPI("localhost", 3000, false)

    private val client = PostEncryptedClient(huginAPI)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all encrypted posts`() = runTest {
        // Arrange

        // Act
        val encryptedPosts = client.getAll()

        // Assert
        assertNotNull(encryptedPosts)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get encrypted post by tx hash`() = runTest {
        // Arrange
        val txHash = ""

        // Act
        val encryptedPostByTxhash = client.getByTxHash(txHash)

        // Assert
        assertNotNull(encryptedPostByTxhash)
    }
}