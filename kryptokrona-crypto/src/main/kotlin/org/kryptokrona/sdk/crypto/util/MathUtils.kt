package org.kryptokrona.sdk.crypto.util

import org.kryptokrona.sdk.crypto.CLibraryLoader

class MathUtils : CLibraryLoader() {
    external fun add(a: Int, b: Int): Int
}