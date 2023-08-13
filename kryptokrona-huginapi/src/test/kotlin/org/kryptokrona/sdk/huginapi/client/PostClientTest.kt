package org.kryptokrona.sdk.huginapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.huginapi.model.HuginAPI
import org.kryptokrona.sdk.huginapi.model.request.SendMessageRequest
import kotlin.test.assertNotNull

class PostClientTest {

    private val huginAPI = HuginAPI("localhost", 3000, false)

    private val client = PostClient(huginAPI)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get info`() = runTest {
        // Arrange
        val sendMessageRequest = SendMessageRequest(payload = "")

        // Act
        val sendMessage = client.sendMessage(sendMessageRequest)

        // Assert
        assertNotNull(sendMessage)
    }
}