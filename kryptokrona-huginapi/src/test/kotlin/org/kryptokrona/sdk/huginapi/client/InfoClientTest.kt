package org.kryptokrona.sdk.huginapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.huginapi.model.HuginAPI
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class InfoClientTest {

    private val huginAPI = HuginAPI("localhost", 3000, false)

    private val client = InfoClient(huginAPI)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get info`() = runTest {
        // Arrange

        // Act
        val info = client.getInfo()

        // Assert
        assertNotNull(info)
        assertTrue { info.donationAddress.isNotEmpty() }
        assertTrue { info.status == "online" || info.status == "offline" }
    }
}