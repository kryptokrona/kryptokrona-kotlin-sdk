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

package org.kryptokrona.sdk.crypto

import org.kryptokrona.sdk.crypto.model.KeyImage
import org.kryptokrona.sdk.crypto.model.WalletKeyPairs
import org.kryptokrona.sdk.crypto.util.convertHexToBytes
import org.kryptokrona.sdk.crypto.util.toHex
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

private const val BYTE_ARRAY_LENGTH = 32 // length of the byte arrays used in the function
private const val BITS_PER_BYTE = 8

private val crypto = Crypto()
private val ed25519 = Ed25519()
private val keccak = Keccak()

/**
 * Generates a signature from a hash and a secret key, and returns the result as a key image.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param derivation
 * @param index
 * @param myPublicSpend
 * @return a key image containing the image and private ephemeral key.
 */
fun getKeyImageFromOutput(derivation: ByteArray, index: Long, myPublicSpend: ByteArray): KeyImage {
    //TODO need to get this from WalletService and pass to this function
    // get our private spend key from wallet.keys
    val privateSpendKey = convertHexToBytes("")
    val publicSpendKey = ByteArray(BYTE_ARRAY_LENGTH)
    val derivedKey = ByteArray(BYTE_ARRAY_LENGTH)

    // derive the key pair
    crypto.derivePublicKey(derivation, index, myPublicSpend, publicSpendKey)
    crypto.deriveSecretKey(derivation, index, privateSpendKey, derivedKey)

    // generate the key image
    val image = ByteArray(BYTE_ARRAY_LENGTH)
    crypto.generateKeyImage(publicSpendKey, privateSpendKey, image)

    // the check is done in bytes, return hex64 strings
    return KeyImage(toHex(image), toHex(derivedKey))
}

/**
 * Generates a PBKDF2 derived key from a password, salt, key length and number of iterations.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param password the password used in the key generation.
 * @param salt the salt used in the key generation.
 * @param keyLength the length of the key.
 * @param iterations the number of iterations.
 * @return a PBKDF2 derived key as ByteArray.
 */
fun generatePBKDF2DerivedKey(password: CharArray, salt: ByteArray, keyLength: Int, iterations: Int): ByteArray {
    val keySpec = PBEKeySpec(password, salt, iterations, keyLength * BITS_PER_BYTE)
    val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
    return keyFactory.generateSecret(keySpec).encoded
}

fun generateKeyPairs(): WalletKeyPairs {
    val publicSpendKey = ByteArray(32)
    val privateSpendKey = ByteArray(64)
    val seed = ByteArray(32)

    val sr: SecureRandom = SecureRandom.getInstance("NativePRNGNonBlocking")
    sr.nextBytes(seed)

    // create the spend key pair
    ed25519.createKeyPair(publicSpendKey, privateSpendKey, seed)

    // compute a hash of the private spend key
    val output = ByteArray(32)
    keccak.computeHashValue(privateSpendKey, 64, output, 32)

    // generate the view key pair
    val publicViewKey = ByteArray(32)
    val privateViewKey = ByteArray(64)
    ed25519.createKeyPair(publicViewKey, privateViewKey, output)

    return WalletKeyPairs(
        publicSpendKey = toHex(publicSpendKey),
        privateSpendKey = toHex(privateSpendKey),
        publicViewKey = toHex(publicViewKey),
        privateViewKey = toHex(privateViewKey)
    )
}

/**
 * Gives the XKR prefix.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @return the XKR prefix.
 */
fun getPrefix(): String {
    return "96d68801"
}

fun generateAddress(publicSpendKey: String, publicViewKey: String): String {
    val bytes = mutableListOf<Byte>()

    // add prefix
    val hexPrefix = getPrefix()
    val prefixBytes = convertHexToBytes(hexPrefix)
    bytes.addAll(prefixBytes.toList())

    // add public keys
    bytes.addAll(publicSpendKey.toByteArray().toList())
    bytes.addAll(publicViewKey.toByteArray().toList())

    // add checksum
    // cn fast hash istället för sha256
    val md = MessageDigest.getInstance("SHA-256")
    val checksum = md.digest(bytes.toByteArray()).take(4)
    bytes.addAll(checksum.toList())

    // convert to base58 in 8 byte chunks
    val base58 = StringBuilder()
    val byteArray = bytes.toByteArray()
    var currentIndex = 0

    while (currentIndex < byteArray.size) {
        val chunkSize = if (currentIndex + 8 <= byteArray.size) 8 else byteArray.size - currentIndex
        val chunk = byteArray.copyOfRange(currentIndex, currentIndex + chunkSize)
        val encodedChunk = Base58.encode(chunk)
        val expectedLength = when (chunkSize) {
            8 -> 11
            6 -> 9
            5 -> 7
            else -> error("Invalid chunk length: $chunkSize")
        }
        val missing = expectedLength - encodedChunk.length
        if (missing > 0) {
            base58.append("1".repeat(missing))
        }
        base58.append(encodedChunk)
        currentIndex += chunkSize
    }

    return base58.toString()
}

