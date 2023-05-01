package org.kryptokrona.sdk.wallet.service

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlinx.coroutines.test.runTest
import org.kryptokrona.sdk.util.model.node.Node
import java.io.File

/**
 * Tests for the WalletService class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class WalletServiceTest {

    private val node = Node("privacymine.net", 11898, false)

    private val walletService = WalletService(node)

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can start syncing`() = runTest {
        // Arrange
        val walletService = WalletService(node)
        walletService.setStartHeight(1364842L)

        // currently we can not stop this job and its childjobs with stopSync() method
        // Act
        /*launch {
            while(isActive) {
                walletService.startSync()
            }
        }

        delay(30_000) // 30 seconds
        walletService.stopSync()
        walletService.getNodeInfo()?.let { println(it) }*/

        // Assert
    }

    @Test
    fun `can save wallet to file`() {
        // Arrange
        val fileName = "test.wallet"
        val password = "strongPassword1432"

        // Act
        walletService.saveWalletToFile(fileName, password)

        // Assert
        val file = File(System.getProperty("user.home"), fileName)
        assertTrue { file.exists() }

        // Clean up
        file.delete()
    }

    @Test
    fun `can load wallet from file`() {
        // Arrange
        val fileName = "test.wallet"
        val password = "strongPassword1432"
        val file = File(System.getProperty("user.home"), fileName)

        // Act
        walletService.saveWalletToFile(fileName, password)
        walletService.loadWalletFromFile(fileName, password)

        // Assert
        assertTrue { walletService.getIsWalletLoaded() }

        // Clean up
        file.delete()
    }
}
