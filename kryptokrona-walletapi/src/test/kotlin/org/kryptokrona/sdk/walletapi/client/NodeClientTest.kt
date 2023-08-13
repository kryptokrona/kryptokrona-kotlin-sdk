package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi

class NodeClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = NodeClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get node details`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can swap node details`() = runTest {
        // Arrange

        // Act

        // Assert
    }
}
