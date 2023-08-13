package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class StatusClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = StatusClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet status`() = runTest {
        // Arrange

        // Act
        val status = client.walletStatus()

        // Assert
        assertNotNull(status?.walletBlockCount)
    }
}