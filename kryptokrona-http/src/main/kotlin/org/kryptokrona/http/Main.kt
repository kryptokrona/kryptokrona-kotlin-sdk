package org.kryptokrona.http

import kotlinx.coroutines.runBlocking
import org.kryptokrona.http.common.*


fun main(args: Array<String>) = runBlocking {

    isNodeRunning().let {
        println("Node is running")
    }

    val info = getNodeInfo()
    info?.let {
        println("Node: ${it}")
    }

    val fee = getNodeFee()
    fee?.let {
        println("Fee: ${it}")
    }

    val height = getNodeHeight()
    height?.let {
        println("Height: ${it}")
    }

    val peers = getNodePeers()
    peers?.let {
        println("Peers: ${it}")
    }

    println("Kotlin End")

}