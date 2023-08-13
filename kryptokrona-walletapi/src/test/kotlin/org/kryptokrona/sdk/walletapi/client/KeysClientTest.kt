package org.kryptokrona.sdk.walletapi.client

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.walletapi.model.WalletApi
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class KeysClientTest {

    private val walletapi = WalletApi("localhost", 8070, "false", "", false)

    private val client = KeysClient(walletapi)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get shared private view key`() = runTest {
        // Arrange

        // Act
        val sharedPrivateViewKey = client.sharedPrivateViewKey()

        // Assert
        assertNotNull(sharedPrivateViewKey)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get spend keys`() = runTest {
        // Arrange
        val correctAddress = ""
        val incorrectAddress = "SEKReV7rDhaj9UV8NYgesQLyAVoddy4foZQzqg2Ci4YrdUNB3qFUzjCQeEe85es2yuC8wE9kGzhcKU23A7Qa5qm9h4CFbKh3umH"

        // Act
        val spendKeysWithCorrectAddress = client.getSpendKeys(correctAddress)
        val spendKeysWithIncorrectAddress = client.getSpendKeys(incorrectAddress)

        // Assert
        assertNotNull(spendKeysWithCorrectAddress)
        assertNull(spendKeysWithIncorrectAddress)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get mnemonic seed`() = runTest {
        // Arrange
        val correctAddress = ""
        val incorrectAddress = "SEKReV7rDhaj9UV8NYgesQLyAVoddy4foZQzqg2Ci4YrdUNB3qFUzjCQeEe85es2yuC8wE9kGzhcKU23A7Qa5qm9h4CFbKh3umH"

        // Act
        val mnemonicSeedWithCorrectAddress = client.getMnemonicSeed(correctAddress)
        val mnemonicSeedWithIncorrectAddress = client.getMnemonicSeed(incorrectAddress)

        // Assert
        assertNotNull(mnemonicSeedWithCorrectAddress)
        assertNull(mnemonicSeedWithIncorrectAddress)
    }
}
