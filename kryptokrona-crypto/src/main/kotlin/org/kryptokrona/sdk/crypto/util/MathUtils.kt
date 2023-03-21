package org.kryptokrona.sdk.crypto.util

import org.kryptokrona.sdk.crypto.RustLibraryLoader

class MathUtils : RustLibraryLoader() {
    external fun add(a: Int, b: Int): Int
}