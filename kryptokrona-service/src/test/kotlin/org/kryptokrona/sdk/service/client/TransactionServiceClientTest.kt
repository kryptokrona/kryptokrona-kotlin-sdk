package org.kryptokrona.sdk.service.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.service.model.Service

class TransactionServiceClientTest {

    private val service = Service("localhost", 8070, "false", "", false)

    private val client = TransactionServiceClient(service)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get transaction hashes`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a list of transactions`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a list of unconfirmed transaction hashes`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a transaction`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send a transaction to the blockchain`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create a delayed transaction`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a list of transaction hashes for all delayed transactions`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can delete a delayed transaction`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send a delayed transacton`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can send a fusion transaction`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can estimate the number of outputs that can be optimized in a fusion transaction`() = runTest {

    }
}