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
import org.kryptokrona.sdk.crypto.util.toHex


private val crypto = Crypto()

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
    val publicSpendKey = ByteArray(32)
    val derivedKey = ByteArray(32)

    // derive the key pair
    crypto.derivePublicKey(derivation, index, myPublicSpend, publicSpendKey)
    crypto.deriveSecretKey(derivation, index, privateSpendKey, derivedKey)

    // generate the key image
    val image = ByteArray(32)
    crypto.generateKeyImage(publicSpendKey, privateSpendKey, image)

    // the check is done in bytes, return hex64 strings
    return KeyImage(toHex(image), toHex(derivedKey))
}
