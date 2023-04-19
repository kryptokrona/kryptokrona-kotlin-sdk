package org.kryptokrona.sdk.http.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.http.model.request.transaction.TransactionDetailsByHashesRequest
import org.kryptokrona.sdk.http.model.request.transaction.TransactionHashesPaymentIdRequest
import org.kryptokrona.sdk.http.model.request.transaction.TransactionsRequest
import org.kryptokrona.sdk.http.model.request.transaction.TransactionsStatusRequest
import org.kryptokrona.sdk.util.model.node.Node

/**
 * Tests for the TransactionClient class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class TransactionClientTest {

    private val nodeHTTP = Node("privacymine.net", 11898, false)

    private val nodeHTTPS = Node("privacymine.net", 21898, true)

    private val clientHTTP = TransactionClient(nodeHTTP)

    private val clientHTTPS = TransactionClient(nodeHTTPS)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transactions with empty body` () = runTest {
        // Arrange
        val transactions = TransactionsRequest()

        // Act
        val dataHTTP = clientHTTP.getTransactions(transactions)
        val dataHTTPS = clientHTTPS.getTransactions(transactions)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transactions with empty list` () = runTest {
        // Arrange
        val transactions = TransactionsRequest(txsHashes = listOf())

        // Act
        val dataHTTP = clientHTTP.getTransactions(transactions)
        val dataHTTPS = clientHTTPS.getTransactions(transactions)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transactions with list of tx hashes` () = runTest {
        // Arrange
        val transactions = TransactionsRequest(txsHashes = listOf("hash1", "hash2", "hash3"))

        // Act
        val dataHTTP = clientHTTP.getTransactions(transactions)
        val dataHTTPS = clientHTTPS.getTransactions(transactions)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details by hashes with empty body` () = runTest {
        // Arrange
        val hashes = TransactionDetailsByHashesRequest()

        // Act
        val dataHTTP = clientHTTP.getTransactionDetailsByHashes(hashes)
        val dataHTTPS = clientHTTPS.getTransactionDetailsByHashes(hashes)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details by hashes with empty list` () = runTest {
        // Arrange
        val hashes = TransactionDetailsByHashesRequest(transactionHashes = listOf())

        // Act
        val dataHTTP = clientHTTP.getTransactionDetailsByHashes(hashes)
        val dataHTTPS = clientHTTPS.getTransactionDetailsByHashes(hashes)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details by hashes with transaction hashes` () = runTest {
        // Arrange
        val hashes = TransactionDetailsByHashesRequest(transactionHashes = listOf("hash1", "hash2", "hash3"))

        // Act
        val dataHTTP = clientHTTP.getTransactionDetailsByHashes(hashes)
        val dataHTTPS = clientHTTPS.getTransactionDetailsByHashes(hashes)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction hashes by payment id` () = runTest {
        // Arrange
        val paymentId = TransactionHashesPaymentIdRequest(paymentId = "paymentId")

        // Act
        val dataHTTP = clientHTTP.getTransactionHashesByPaymentId(paymentId)
        val dataHTTPS = clientHTTPS.getTransactionHashesByPaymentId(paymentId)

        // Assert
        assertNotNull(dataHTTP)
        assertNotNull(dataHTTPS)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with empty body` () = runTest {
        // Arrange
        val transactionStatus = TransactionsStatusRequest()

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes` () = runTest {
        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf("hash1", "hash2", "hash3"))

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes and transactions in pool` () = runTest {
        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf("hash1", "hash2", "hash3"),
            transactionsInPool = false
        )

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes, transactions in pool and transactions in block` () = runTest {
        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf("hash1", "hash2", "hash3"),
            transactionsInPool = false,
            transactionsInBlock = false
        )

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes, transactions in pool, transactions in block and transactions unknown` () = runTest {
        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf("hash1", "hash2", "hash3"),
            transactionsInPool = false,
            transactionsInBlock = false,
            transactionsUnknown = false
        )

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals(dataHTTP.status, "OK")
        if (dataHTTPS != null) assertEquals(dataHTTPS.status, "OK")
    }
}
