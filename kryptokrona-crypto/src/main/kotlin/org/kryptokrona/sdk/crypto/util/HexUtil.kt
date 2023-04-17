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

package org.kryptokrona.sdk.crypto.util

/**
 * Utility function for converting between hex and byte arrays.
 *
 * @since 0.2.0
 * @param bytes the byte array to convert
 * @return the hex string
 */
fun toHex(bytes: ByteArray): String {
    val hexChars = "0123456789abcdef".toCharArray()
    val result = StringBuilder(bytes.size * 2)

    for (byte in bytes) {
        val v = byte.toInt() and 0xff
        result.append(hexChars[v shr 4])
        result.append(hexChars[v and 0x0f])
    }

    return result.toString()
}

/**
 * Utility function for converting between hex and byte arrays.
 *
 * @since 0.2.0
 * @param string the hex string to convert
 * @return the byte array
 */
fun fromHex(string: String): ByteArray {
    if (string.length % 2 != 0) {
        throw IllegalArgumentException("Input must have an even number of characters")
    }
    val hexChars = "0123456789abcdef".toCharArray()
    val result = ByteArray(string.length / 2)

    for (i in string.indices step 2) {
        val firstIndex = hexChars.indexOf(string[i].toLowerCase())
        val secondIndex = hexChars.indexOf(string[i + 1].toLowerCase())
        if (firstIndex == -1 || secondIndex == -1) {
            throw IllegalArgumentException("Input contains invalid characters")
        }
        val byteValue = (firstIndex shl 4) or secondIndex
        result[i / 2] = byteValue.toByte()
    }

    return result
}

/**
 * Utility function for converting between hex and byte arrays.
 *
 * @since 0.2.0
 * @param hex the hex string to convert
 * @return the byte array
 */
fun convertHexToBytes(hex: String): ByteArray {
    if (hex.length % 2 != 0) {
        throw IllegalArgumentException("Input string must have an even number of characters.")
    }

    val bytes = ByteArray(hex.length / 2)
    hex.chunked(2).forEachIndexed { i, byte ->
        val byteValue = byte.toIntOrNull(16)
            ?: throw IllegalArgumentException("Invalid character(s): $byte.")
        bytes[i] = byteValue.toByte()
    }
    return bytes
}
