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
import org.kryptokrona.sdk.crypto.util.convertHexToBytes
import org.kryptokrona.sdk.crypto.util.fromHex
import org.kryptokrona.sdk.crypto.util.toHex

/**
 * Crypto class that loads the C library
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class Crypto : CLibraryLoader() {

    /**
     * Generates a key derivation from a public key and a secret key, and stores the result in the provided buffer.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param publicKey the public key used in the key derivation.
     * @param secretKey the secret key used in the key derivation.
     * @param keyDerivation the buffer to store the generated key derivation.
     * @return the number of bytes written to the key derivation buffer.
     */
    external fun generateKeyDerivation(publicKey: ByteArray, secretKey: ByteArray, keyDerivation: ByteArray): Int

    /**
     * Derives a public key from a base key and a key derivation, and stores the result in the provided buffer.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param derivation the key derivation used in the public key derivation.
     * @param outputIndex the index of the output in the derivation path.
     * @param derivedKey the buffer to store the derived public key.
     * @param base the base key used in the public key derivation.
     * @return the number of bytes written to the derived public key buffer.
     */
    external fun underivePublicKey(derivation: ByteArray, outputIndex: Long, derivedKey: ByteArray, base: ByteArray): Int

    /**
     * Generates a key image from a public key and a secret key, and stores the result in the provided buffer.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param pub the public key used in the key image generation.
     * @param sec the secret key used in the key image generation.
     * @param image the buffer to store the generated key image.
     */
    external fun generateKeyImage(pub: ByteArray, sec: ByteArray, image: ByteArray): Int

    /**
     * Generates a key pair from a secret key, and stores the result in the provided buffers.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param pub the buffer to store the generated public key.
     * @param sec the secret key used in the key pair generation.
     * @param recoverable if true, the public key is recoverable from the secret key.
     * @return the number of bytes written to the public key buffer.
     */
    external fun derivePublicKey(derivation: ByteArray, outputIndex: Long, base: ByteArray): Int

    /**
     * Derives a secret key from a base key and a key derivation, and stores the result in the provided buffer.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param derivation the key derivation used in the secret key derivation.
     * @param outputIndex the index of the output in the derivation path.
     * @param derivedKey the buffer to store the derived secret key.
     * @param base the base key used in the secret key derivation.
     * @return the number of bytes written to the derived secret key buffer.
     */
    external fun deriveSecretKey(derivation: ByteArray, outputIndex: Long, base: ByteArray): Int

    /**
     * Generates a signature from a hash and a secret key, and returns the result as a key image.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param hash the hash used in the signature generation.
     * @param sec the secret key used in the signature generation.
     * @param sig the buffer to store the generated signature.
     * @return a key image containing the image and private ephemeral key.
     */
    fun getKeyImageFromOutput(derivation: ByteArray, index: Long, myPublicSpend: ByteArray): KeyImage {
        //TODO need to get this from WalletService and pass to this function
        // get our private spend key from wallet.keys
        val privateSpendKey = convertHexToBytes("")

        // derive the ephemeral key pair
        val publicEphemeral = derivePublicKey(derivation, index, myPublicSpend)
        val privateEphemeral = deriveSecretKey(derivation, index, privateSpendKey)

        // generate the key image
        val image = ByteArray(32)
        generateKeyImage(publicEphemeral, privateEphemeral, image)

        // the check is done in bytes, return hex64 strings
        return KeyImage(toHex(image), toHex(privateEphemeral))  // should use toHex on string values that we get
    }
}