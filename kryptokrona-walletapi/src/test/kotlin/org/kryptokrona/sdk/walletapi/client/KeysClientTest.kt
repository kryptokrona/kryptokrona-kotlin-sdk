package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi

class KeysClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = KeysClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get shared private view key`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get spend keys`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get mnemonic seed`() = runTest {
        // Arrange

        // Act

        // Assert
    }
}
