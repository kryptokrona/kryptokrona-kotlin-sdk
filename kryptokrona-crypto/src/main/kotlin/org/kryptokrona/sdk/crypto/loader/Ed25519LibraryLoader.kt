// Copyright (c) 2022-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.sdk.crypto.loader

import java.io.File
import java.io.FileNotFoundException
import java.util.*

/**
 * A class that loads the ed25519 library
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
open class Ed25519LibraryLoader {

    init {
        System.load(getLibraryPath())
    }

    /**
     * Returns the path to the ed25519 shared library.
     *
     * If the current thread is running inside a unit test, the library is loaded from the current working directory.
     * Otherwise, the library is loaded from the classpath.
     * This is done to make it easier to run the unit tests.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @return the path to the ed25519 shared library
     * @throws RuntimeException if the library is not found
     */
    private fun getLibraryPath(): String {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
        val libraryName = when {
            osName.contains("windows") -> "ed25519.dll"
            osName.contains("mac") -> "libed25519.dylib"
            else -> "libed25519.so"
        }

        // use a different path for tests
        val libraryFolder = if (isRunningInsideUnitTest()) "../kryptokrona-crypto/build/libs"
                            else "kryptokrona-crypto/build/libs"

        val userDir = System.getProperty("user.dir")
        val libraryPath = File(userDir, "$libraryFolder/$libraryName")

        if (!libraryPath.exists()) {
            throw FileNotFoundException("Failed to find the crypto shared library: $libraryName")
        }

        return libraryPath.absolutePath
    }

    /**
     * Checks if the current thread is running inside a unit test.
     *
     * This is used to determine if we should load the native library from the
     * current working directory or from the classpath.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @return true if the current thread is running inside a unit test, false otherwise
     */
    private fun isRunningInsideUnitTest(): Boolean {
        val stackTrace = Thread.currentThread().stackTrace
        return stackTrace.any { it.className.startsWith("org.junit.") }
    }
}
