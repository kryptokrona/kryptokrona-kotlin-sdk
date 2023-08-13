package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi
import kotlin.test.assertNotNull

class NodeClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = NodeClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node details`() = runTest {
        // Arrange

        // Act
        val nodeDetails = client.nodeDetails()

        // Assert
        assertNotNull(nodeDetails)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can swap node details`() = runTest {
        // Arrange

        // Act
        val swapNodeDetails = client.swapNodeDetails()

        // Assert
        assertNotNull(swapNodeDetails)
    }
}
