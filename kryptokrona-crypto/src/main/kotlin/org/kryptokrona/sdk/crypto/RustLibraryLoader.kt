package org.kryptokrona.sdk.crypto

open class RustLibraryLoader {
    init {
        System.loadLibrary("crypto")
    }
}