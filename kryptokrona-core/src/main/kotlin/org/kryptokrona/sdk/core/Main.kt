package org.kryptokrona.sdk.core

import kotlinx.coroutines.*
import org.kryptokrona.sdk.util.node.Node
import org.kryptokrona.sdk.core.service.WalletService

suspend fun main(args: Array<String>) = coroutineScope {
    val node = Node("privacymine.net", 11898, false)
    val walletService = WalletService(node)

    val deferred = async { walletService.startSync() }

    delay(60*1000)

    walletService.stopSync()
}