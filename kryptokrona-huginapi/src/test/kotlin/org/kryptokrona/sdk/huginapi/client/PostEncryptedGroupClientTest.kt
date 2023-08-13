package org.kryptokrona.sdk.huginapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.huginapi.model.HuginAPI

class PostEncryptedGroupClientTest {

    private val huginAPI = HuginAPI("localhost", 3000, false)

    private val client = PostEncryptedGroupClient(huginAPI)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all encrypted group posts`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get encrypted group post by tx hash`() = runTest {
        // Arrange

        // Act

        // Assert
    }
}