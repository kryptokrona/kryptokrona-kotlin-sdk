package org.kryptokrona.http

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kryptokrona.http.common.get


fun main(args: Array<String>) = runBlocking {

    println("Kotlin Start")

    launch {
        delay(2000)
        println("Kotlin Inside")

    }
    println("Kotlin End")
}