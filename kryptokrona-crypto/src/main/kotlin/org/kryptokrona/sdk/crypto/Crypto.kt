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
     * @return if successful or not in the form of an integer.
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
     * @return if successful or not in the form of an integer.
     */
    external fun underivePublicKey(
        derivation: ByteArray,
        outputIndex: Long,
        derivedKey: ByteArray,
        base: ByteArray): Int

    /**
     * Generates a key image from a public key and a secret key, and stores the result in the provided buffer.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param pub the public key used in the key image generation.
     * @param sec the secret key used in the key image generation.
     * @param image the buffer to store the generated key image.
     */
    external fun generateKeyImage(pub: ByteArray, sec: ByteArray, image: ByteArray)

    /**
     * Generates a key pair from a secret key, and stores the result in the provided buffers.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param derivation the buffer to store the generated public key.
     * @param outputIndex the buffer to store the generated secret key.
     * @param base the secret key used in the key pair generation.
     * @param derivedKey the secret key used in the key pair generation.
     * @return if successful or not in the form of an integer.
     */
    external fun derivePublicKey(derivation: ByteArray, outputIndex: Long, base: ByteArray, derivedKey: ByteArray): Int

    /**
     * Derives a secret key from a base key and a key derivation, and stores the result in the provided buffer.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param derivation the key derivation used in the secret key derivation.
     * @param outputIndex the index of the output in the derivation path.
     * @param base the base key used in the secret key derivation.
     * @param derivedKey the buffer to store the derived secret key.
     */
    external fun deriveSecretKey(derivation: ByteArray, outputIndex: Long, base: ByteArray, derivedKey: ByteArray)
}
