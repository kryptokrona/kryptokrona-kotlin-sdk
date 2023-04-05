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
        var data = clientHTTP.getTransactions()
        assertNotNull(data)

        data = clientHTTPS.getTransactions()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction details by hashes` () = runTest {
        var data = clientHTTP.getTransactionDetailsByHashes()
        assertNotNull(data)

        data = clientHTTPS.getTransactionDetailsByHashes()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction hashes by payment id` () = runTest {
        var data = clientHTTP.getTransactionHashesByPaymentId()
        assertNotNull(data)

        data = clientHTTPS.getTransactionHashesByPaymentId()
        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction status` () = runTest {
        var data = clientHTTP.getTransactionsStatus()
        assertNotNull(data)

        data = clientHTTPS.getTransactionsStatus()
        assertNotNull(data)
    }

}