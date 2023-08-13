package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi

class AddressClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = AddressClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get primary address`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a list of addresses`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create a random address`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import an address with secret spend key`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import a view only address with a public spend key`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can delete a given address`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create an integrated address from a given address and payment id`() = runTest {
        // Arrange

        // Act

        // Assert
    }
}
