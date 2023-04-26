package org.kryptokrona.sdk.core.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
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
