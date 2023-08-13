package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi
import org.kryptokrona.sdk.walletapi.model.request.SendTransactionRequest
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TransactionClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = TransactionClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send a transaction`() = runTest {
        // Arrange
        val sendTransactionRequest = SendTransactionRequest(
            destination = "",
            amount = 0L,
            paymentID = ""
        )

        // Act
        val sendTransaction = client.sendTransaction(sendTransactionRequest)

        // Assert
        assertNotNull(sendTransaction)
        assertTrue { sendTransaction.transactionHash.isNotEmpty() }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send an advanced transaction`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send a fusion transaction`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send an advanced fusion transaction`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all transactions`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all unconfirmed transactions`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all unconfirmed transactions with a given address`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get all transactions starting and ending at a given block belonging to given address`() = runTest {
        // Arrange

        // Act

        // Assert
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details with a given hash`() = runTest {
        // Arrange

        // Act

        // Assert
    }
}
