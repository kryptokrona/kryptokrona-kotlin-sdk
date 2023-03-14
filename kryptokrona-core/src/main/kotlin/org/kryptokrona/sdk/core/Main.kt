package org.kryptokrona.sdk.core

import kotlinx.coroutines.runBlocking
import org.kryptokrona.sdk.core.node.Node
import org.kryptokrona.sdk.core.service.WalletService

fun main(args: Array<String>) = runBlocking {
    val node = Node("privacymine.net", 11898, false)
    val walletService = WalletService(node)

    walletService.startSync()

    walletService.stopSync()
}