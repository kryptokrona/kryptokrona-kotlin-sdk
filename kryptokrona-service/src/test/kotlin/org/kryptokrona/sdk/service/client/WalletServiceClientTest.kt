package org.kryptokrona.sdk.service.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.service.model.Service

class WalletServiceClientTest {

    private val service = Service("localhost", 8070, "false", "", false)

    private val client = WalletServiceClient(service)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can save wallet`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can reset wallet`() = runTest {

    }
}