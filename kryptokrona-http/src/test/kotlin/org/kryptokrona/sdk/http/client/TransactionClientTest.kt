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

    private val nodeHTTP = Node("blocksum.org", 11898, false)

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
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
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
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transactions with list of tx hashes` () = runTest {
        // Using Block 2e9a0f1fb6e59a76984bce3a390b9484c3d11ea3c36ba750147efe822c8a9f5b

        // Arrange
        val transactions = TransactionsRequest(
            txsHashes = listOf(
                "9f20011eee4d97227e17805f8c8cce1bb833e8f44b1c0d8f42c0271f500a1be8",
                "c67d90f2ba8d855f2217f884d86dbb47bb043954573867f9824cbe5ad968ee62",
                "971ec6b759ae26b4e52550e2d0a88259f49d5279f32848b4821ddeb99c368f12",
                "8ca57276ec06bf987d6774cafa913092b0bcd86aa8bd697339612e7f51b7909d"
            ))

        // Act
        val dataHTTP = clientHTTP.getTransactions(transactions)
        val dataHTTPS = clientHTTPS.getTransactions(transactions)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
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
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
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
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details by hashes with transaction hashes` () = runTest {
        // Using Block 2e9a0f1fb6e59a76984bce3a390b9484c3d11ea3c36ba750147efe822c8a9f5b

        // Arrange
        val hashes = TransactionDetailsByHashesRequest(
            transactionHashes = listOf(
                "9f20011eee4d97227e17805f8c8cce1bb833e8f44b1c0d8f42c0271f500a1be8",
                "c67d90f2ba8d855f2217f884d86dbb47bb043954573867f9824cbe5ad968ee62",
                "971ec6b759ae26b4e52550e2d0a88259f49d5279f32848b4821ddeb99c368f12",
                "8ca57276ec06bf987d6774cafa913092b0bcd86aa8bd697339612e7f51b7909d"
            ))

        // Act
        val dataHTTP = clientHTTP.getTransactionDetailsByHashes(hashes)
        val dataHTTPS = clientHTTPS.getTransactionDetailsByHashes(hashes)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    //TODO - this test works inconsistently with the following error response:
    // {
    //    "status": "Error: _Map_base::at",
    //    "transactionHashes": []
    // }
    // This is an issue with the kryptokrona core rpc server.
    /*@Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction hashes by payment id` () = runTest {
        // Arrange
        val paymentId = TransactionHashesPaymentIdRequest(
            paymentId = "e94f0ecf94fe63be4ad3ecfd2de2bb95a950f37b72991caac20771620c091e0f")

        // Act
        val dataHTTP = clientHTTP.getTransactionHashesByPaymentId(paymentId)
        val dataHTTPS = clientHTTPS.getTransactionHashesByPaymentId(paymentId)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }*/

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with empty body` () = runTest {
        // Arrange
        val transactionStatus = TransactionsStatusRequest()

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes` () = runTest {
        // Using Block 2e9a0f1fb6e59a76984bce3a390b9484c3d11ea3c36ba750147efe822c8a9f5b

        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf(
                "9f20011eee4d97227e17805f8c8cce1bb833e8f44b1c0d8f42c0271f500a1be8",
                "c67d90f2ba8d855f2217f884d86dbb47bb043954573867f9824cbe5ad968ee62",
                "971ec6b759ae26b4e52550e2d0a88259f49d5279f32848b4821ddeb99c368f12",
                "8ca57276ec06bf987d6774cafa913092b0bcd86aa8bd697339612e7f51b7909d"
            ))

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes and transactions in pool` () = runTest {
        // Using Block 2e9a0f1fb6e59a76984bce3a390b9484c3d11ea3c36ba750147efe822c8a9f5b

        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf(
                "9f20011eee4d97227e17805f8c8cce1bb833e8f44b1c0d8f42c0271f500a1be8",
                "c67d90f2ba8d855f2217f884d86dbb47bb043954573867f9824cbe5ad968ee62",
                "971ec6b759ae26b4e52550e2d0a88259f49d5279f32848b4821ddeb99c368f12",
                "8ca57276ec06bf987d6774cafa913092b0bcd86aa8bd697339612e7f51b7909d"
            ),
            transactionsInPool = false
        )

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes, transactions in pool and transactions in block` () = runTest {
        // Using Block 2e9a0f1fb6e59a76984bce3a390b9484c3d11ea3c36ba750147efe822c8a9f5b

        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf(
                "9f20011eee4d97227e17805f8c8cce1bb833e8f44b1c0d8f42c0271f500a1be8",
                "c67d90f2ba8d855f2217f884d86dbb47bb043954573867f9824cbe5ad968ee62",
                "971ec6b759ae26b4e52550e2d0a88259f49d5279f32848b4821ddeb99c368f12",
                "8ca57276ec06bf987d6774cafa913092b0bcd86aa8bd697339612e7f51b7909d"
            ),
            transactionsInPool = false,
            transactionsInBlock = false
        )

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status with transaction hashes, transactions in pool, transactions in block and transactions unknown` () = runTest {
        // Using Block 2e9a0f1fb6e59a76984bce3a390b9484c3d11ea3c36ba750147efe822c8a9f5b

        // Arrange
        val transactionStatus = TransactionsStatusRequest(
            transactionHashes = listOf(
                "9f20011eee4d97227e17805f8c8cce1bb833e8f44b1c0d8f42c0271f500a1be8",
                "c67d90f2ba8d855f2217f884d86dbb47bb043954573867f9824cbe5ad968ee62",
                "971ec6b759ae26b4e52550e2d0a88259f49d5279f32848b4821ddeb99c368f12",
                "8ca57276ec06bf987d6774cafa913092b0bcd86aa8bd697339612e7f51b7909d"
            ),
            transactionsInPool = false,
            transactionsInBlock = false,
            transactionsUnknown = false
        )

        // Act
        val dataHTTP = clientHTTP.getTransactionsStatus(transactionStatus)
        val dataHTTPS = clientHTTPS.getTransactionsStatus(transactionStatus)

        // Assert
        if (dataHTTP != null) assertEquals("OK", dataHTTP.status)
        if (dataHTTPS != null) assertEquals("OK", dataHTTPS.status)
    }
}
