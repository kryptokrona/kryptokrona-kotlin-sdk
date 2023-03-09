package org.kryptokrona.http

import kotlinx.coroutines.runBlocking
import org.kryptokrona.http.common.*


fun main(args: Array<String>) = runBlocking {

    // TODO: should we rename the http to rpc?

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

    val randomOutputs = getRandomOuts()
    randomOutputs?.let {
        println("Random Outputs: ${it}")
    }

    val oIndexes = getOIndexes()
    oIndexes?.let {
        println("O Indexes: ${it}")
    }

    val globalIndexesForRange = getGlobalIndexesForRange()
    globalIndexesForRange?.let {
        println("Global Indexes for Range: ${it}")
    }

    println("Kotlin End")

}