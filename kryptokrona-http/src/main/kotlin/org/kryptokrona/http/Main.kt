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

    val poolChangesLite = getPoolChangesLite()
    poolChangesLite?.let {
        println("Pool Changes Lite: ${it}")
    }

    val queryBlocks = getQueryBlocks()
    queryBlocks?.let {
        println("Query Blocks: ${it}")
    }

    val queryBlocksLite = getQueryBlocksLite()
    queryBlocksLite?.let {
        println("Query Blocks Lite: ${it}")
    }

    val blockDetailsByHeight = getBlockDetailsByHeight()
    blockDetailsByHeight?.let {
        println("Block Detail By Height: ${it}")
    }

    val blocksDetailsByHeight = getBlocksDetailsByHeights()
    blocksDetailsByHeight?.let {
        println("Blocks Details By Height: ${it}")
    }

    val blocksDetailsHashes = getBlocksDetailsByHashes()
    blocksDetailsHashes?.let {
        println("Block Detail By Hashes: ${it}")
    }

    println("Kotlin End")

}