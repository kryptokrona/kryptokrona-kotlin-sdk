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

package org.kryptokrona.sdk.crypto.mnemonics

/**
 * Use a mnemonic seed phrase to generate a private key.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param words The mnemonic seed phrase.
 * @return The private key.
 */
fun mnemonicToPrivateKey(words: List<String>) {
    TODO()
}

/**
 * Use a mnemonic seed phrase to generate a public key.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param words The mnemonic seed phrase.
 * @return The public key.
 */
fun mnemonicToPublicKey(words: List<String>) {
    TODO()
}

/**
 * Use a private key to generate a mnemonic seed phrase.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param privateKey The private key.
 * @return The mnemonic seed phrase.
 */
fun privateKeyToMnemonic(privateKey: String): List<String> {
    TODO()
}

/**
 * Check if a mnemonic word is valid.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param word The mnemonic word.
 * @return True if the word is valid, false otherwise.
 */
fun isValidMnemonicWord(word: String): Boolean {
    return WordList.words.contains(word)
}
