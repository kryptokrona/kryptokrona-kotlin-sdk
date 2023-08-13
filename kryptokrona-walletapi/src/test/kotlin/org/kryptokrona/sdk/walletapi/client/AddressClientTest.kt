package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi
import org.kryptokrona.sdk.walletapi.model.request.ImportAddressRequest
import org.kryptokrona.sdk.walletapi.model.request.ImportViewOnlyAddressRequest
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AddressClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = AddressClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get primary address`() = runTest {
        // Arrange

        // Act
        val primaryAddress = client.primaryAddress()

        // Assert
        assertNotNull(primaryAddress)
        assertTrue { primaryAddress.address.isNotEmpty() }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a list of addresses`() = runTest {
        // Arrange

        // Act
        val addresses = client.addresses()

        // Assert
        assertNotNull(addresses)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create a random address`() = runTest {
        // Arrange

        // Act
        val createRandomAddress = client.createRandomAddress()

        // Assert
        assertNotNull(createRandomAddress)
        assertTrue { createRandomAddress.address.isNotEmpty() }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import an address with secret spend key`() = runTest {
        // Arrange
        val importAddressRequest = ImportAddressRequest(
            scanHeight = 1000000,
            privateSpendKey = ""
        )

        // Act
        val importAddress = client.importAddress(importAddressRequest)

        // Assert
        assertNotNull(importAddress)
        assertTrue { importAddress.address.isNotEmpty() }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import a view only address with a public spend key`() = runTest {
        // Arrange
        val importViewOnlyAddressRequest = ImportViewOnlyAddressRequest(
            scanHeight = 1000000,
            publicSpendKey = ""
        )

        // Act
        val importViewOnlyAddress = client.importViewOnlyAddress(importViewOnlyAddressRequest)

        // Assert
        assertNotNull(importViewOnlyAddress)
        assertTrue { importViewOnlyAddress.address.isNotEmpty() }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can delete a given address`() = runTest {
        // Arrange
        val address = ""

        // Act
        val deleteAddress = client.deleteAddress(address)

        // Assert
        assertNotNull(deleteAddress)
        assertTrue { deleteAddress.status == 0 } //TODO check if this is the correct status code
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create an integrated address from a given address and payment id`() = runTest {
        // Arrange
        val address = ""
        val paymentId = ""

        // Act
        val createIntegratedAddress = client.createIntegratedAddressFromAddressAndPaymentID(address, paymentId)

        // Assert
        assertNotNull(createIntegratedAddress)
        assertTrue { createIntegratedAddress.integratedAddress.isNotEmpty() }
    }
}
