package org.kryptokrona.http

import kotlinx.coroutines.runBlocking
import org.kryptokrona.http.node.getNodeFee
import org.kryptokrona.http.node.getNodeInfo
import org.kryptokrona.http.node.isNodeRunning


fun main(args: Array<String>) = runBlocking {

    println("Kotlin Start")

    isNodeRunning().let {
        println("Node is running")
    }

    val info = getNodeInfo()
    info?.let {
        println("Node info: ${it}")
    }

    val fee = getNodeFee()
    fee?.let {
        println("Fee info: ${it}")
    }

    println("Kotlin End")
}