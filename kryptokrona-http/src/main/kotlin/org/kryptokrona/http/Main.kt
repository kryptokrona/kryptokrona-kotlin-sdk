package org.kryptokrona.http

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kryptokrona.http.common.get


fun main(args: Array<String>) {
    GlobalScope.launch {
        get()
        delay(5000)
    }

    print("Ending coroutine...")
}