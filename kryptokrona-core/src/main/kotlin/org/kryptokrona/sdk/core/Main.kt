package org.kryptokrona.sdk.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.kryptokrona.sdk.core.node.Node
import org.kryptokrona.sdk.core.service.WalletService

fun main(args: Array<String>) = runBlocking {
    val node = Node("privacymine.net", 11898, false)
    val walletService = WalletService(node)

    walletService.startSync()

    delay(5000)

    walletService.stopSync()
}