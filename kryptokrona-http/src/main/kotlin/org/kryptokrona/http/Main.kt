package org.kryptokrona.http

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.kryptokrona.http.model.Info
import org.kryptokrona.http.node.getNodeInfo
import org.kryptokrona.http.node.isNodeRunning


fun main(args: Array<String>) = runBlocking {

    println("Kotlin Start")

    isNodeRunning().let {
        println("Node is running")
    }

    val test = getNodeInfo()
    test?.let {
        println("Node info: ${it}")
    }
    // val car: Info = Json.decodeFromString(test)

    /*info?.let {
        println("Node info: ${it.difficulty}")
    }*/

    println("Kotlin End")
}