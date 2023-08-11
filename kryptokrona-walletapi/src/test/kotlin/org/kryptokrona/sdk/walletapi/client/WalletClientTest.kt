package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi

class WalletClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = AddressClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can open a wallet`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import wallet with key`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import wallet with seed`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can import view wallet`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create wallet`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can delete wallet`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can save wallet`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can reset wallet`() = runTest {

    }
}