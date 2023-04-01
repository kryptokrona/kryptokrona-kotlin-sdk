package org.kryptokrona.sdk.crypto

import java.io.File
import java.util.*

open class CLibraryLoader {
    init {
        System.load(getLibraryPath())
    }

    private fun getLibraryPath(): String {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
        val libraryName = when {
            osName.contains("windows") -> "crypto.dll"
            osName.contains("mac") -> "libcrypto.dylib"
            else -> "libcrypto.so"
        }

        val userDir = System.getProperty("user.dir")
        val libraryPath = File(userDir, "kryptokrona-crypto/build/libs/$libraryName")

        if (!libraryPath.exists()) {
            throw RuntimeException("Failed to find the C shared library: $libraryName")
        }

        return libraryPath.absolutePath
    }

}
