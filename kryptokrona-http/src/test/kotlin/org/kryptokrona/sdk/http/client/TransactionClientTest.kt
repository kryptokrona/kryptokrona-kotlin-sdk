package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.util.node.Node

class TransactionClientTest {

    private val nodeHTTP = Node("techy.ddns.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = TransactionClient(nodeHTTP)

    private val clientHTTPS = TransactionClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transactions` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getTransactions()
        val dataHTTPS = clientHTTPS.getTransactions()

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details by hashes` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getTransactionDetailsByHashes()
        val dataHTTPS = clientHTTPS.getTransactionDetailsByHashes()

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction hashes by payment id` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getTransactionHashesByPaymentId()
        val dataHTTPS = clientHTTPS.getTransactionHashesByPaymentId()

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status` () = runTest {
        // Arrange

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus()
        val dataHTTPS = clientHTTPS.getTransactionsStatus()

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

}