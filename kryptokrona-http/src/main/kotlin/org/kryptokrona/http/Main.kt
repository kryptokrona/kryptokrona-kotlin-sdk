package org.kryptokrona.http

import kotlinx.coroutines.runBlocking
import org.kryptokrona.core.node.Node
import org.kryptokrona.http.client.*


fun main(args: Array<String>) = runBlocking {

    val node = Node("privacymine.net", 11898, false)
    val blockClient = BlockClient(node)

    blockClient.getBlocks().let {
        println("Blocks: ${it}")
    }
}