package org.kryptokrona.http

import kotlinx.coroutines.runBlocking
import org.kryptokrona.http.common.get


fun main(args: Array<String>) {

    runBlocking { get() }

    print("Ending coroutine...")
}