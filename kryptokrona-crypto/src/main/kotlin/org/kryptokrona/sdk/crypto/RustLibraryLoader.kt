package org.kryptokrona.sdk.crypto

import java.io.File
import java.util.*

open class RustLibraryLoader {
    init {
        println("Loading Rust library...")
        val libraryPath = "/Users/marcus.cvjeticanin/Repos/Kryptokrona/kryptokrona-kotlin-sdk/kryptokrona-crypto/build/libs/libcrypto.dylib"
        System.load(libraryPath)
    }

    private fun getLibraryPath(): String {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
        val libraryName = when {
            osName.contains("windows") -> "crypto.dll"
            osName.contains("mac") -> "libcrypto.dylib"
            else -> "libcrypto.so"
        }

        val userDir = System.getProperty("user.dir")
        val libraryPath = File(userDir, "build/libs/$libraryName")

        if (!libraryPath.exists()) {
            throw RuntimeException("Failed to find the Rust shared library: $libraryName")
        }

        return libraryPath.absolutePath
    }

}
