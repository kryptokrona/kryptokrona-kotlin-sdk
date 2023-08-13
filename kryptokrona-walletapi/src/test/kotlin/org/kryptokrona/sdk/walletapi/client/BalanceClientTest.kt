package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi
import kotlin.test.DefaultAsserter.assertNotNull
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BalanceClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = BalanceClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet balance`() = runTest {
        // Arrange

        // Act
        val balance = client.walletBalance()

        // Assert
        assertNotNull(balance)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet balances`() = runTest {
        // Arrange

        // Act
        val balances = client.walletBalances()

        // Assert
        assertNotNull(balances)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get wallet blanace for specific address`() = runTest {
        // Arrange

        // Act
        //TODO need to generate an address for the testing and put here
        val balanceForCorrectAddress = client.walletBalanceForSpecificAddress(
            ""
        )

        val balanceForIncorrectAddress = client.walletBalanceForSpecificAddress(
            "SEKReV7rDhaj9UV8NYgesQLyAVoddy4foZQzqg2Ci4YrdUNB3qFUzjCQeEe85es2yuC8wE9kGzhcKU23A7Qa5qm9h4CFbKh3umH"
        )

        // Assert
        assertNotNull(balanceForCorrectAddress)
        assertNull(balanceForIncorrectAddress)
    }
}