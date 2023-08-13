package org.kryptokrona.sdk.huginapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.huginapi.model.HuginAPI

class PostEncryptedGroupClientTest {

    private val huginAPI = HuginAPI("localhost", 3000, false)

    private val client = PostEncryptedGroupClient(huginAPI)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get info`() = runTest {
        // Arrange

        // Act

        // Assert
    }
}