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

import org.kryptokrona.sdk.util.model.wallet.WalletFile
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * This class is used to encrypt and decrypt wallet files.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 * @param walletFile The wallet file object to encrypt
 */
class WalletEncryption(private val walletFile: WalletFile? = null) {

    private val logger = LoggerFactory.getLogger("WalletEncryption")

    /**
     * Encrypt the wallet file object with the password using AES encryption.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param password The password to encrypt the wallet with
     */
    fun encryptToFile(fileName: String, password: String) {
        // generate a 256-bit AES key
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256)
        val secretKey: SecretKey = keyGen.generateKey()

        // create cipher object for encryption
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        // get IV (initialization vector) from cipher object
        val iv = cipher.iv

        // serialize the wallet object to a byte array
        walletFile.let {
            it?.let { walletFile ->
                val walletBytes = serialize(walletFile)

                // encrypt the wallet with the password using AES encryption
                val encryptedWallet = cipher.doFinal(walletBytes)

                // concatenate IV and encrypted wallet into a single byte array
                val encryptedBytes = ByteArray(iv.size + encryptedWallet.size)
                System.arraycopy(iv, 0, encryptedBytes, 0, iv.size)
                System.arraycopy(encryptedWallet, 0, encryptedBytes, iv.size, encryptedWallet.size)

                // write encrypted byte array to file
                val homeDir = System.getProperty("user.home")
                val file = File(homeDir, fileName)
                FileOutputStream(file).use { outputStream ->
                    outputStream.write(encryptedBytes)
                }
            }
        } ?: logger.error("Can not encrypt to file when wallet file object is null!")
    }

    /**
     * Load the wallet file object from a file.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param fileName The name of the wallet file
     * @param password The password to decrypt the wallet with
     * @return the deserialized wallet file object
     */
    fun loadWallet(fileName: String, password: String): WalletFile {
        logger.debug("Loading wallet from file...")

        // load encrypted bytes from file
        val encryptedBytes = loadEncryptedBytesFromFile(fileName)

        // split encrypted bytes into IV and ciphertext
        val iv = encryptedBytes.sliceArray(0 until 12)
        val ciphertext = encryptedBytes.sliceArray(12 until encryptedBytes.size)

        // decrypt ciphertext with password and IV using AES encryption
        val decryptedBytes = decryptWallet(ciphertext, password, iv)

        // TODO deserialize decrypted bytes into WalletFile object

        // add the data to the wallet file object
        val walletFile = WalletFile("")

        return walletFile
    }

    /**
     * Deserialize the wallet file object from a byte array.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param fileName The name of the wallet file
     * @return the deserialized wallet file object in byte array
     */
    private fun loadEncryptedBytesFromFile(fileName: String): ByteArray {
        logger.debug("Loading encrypted bytes from file...")

        // read encrypted bytes from file
        val homeDir = System.getProperty("user.home")
        val file = File(homeDir, fileName)
        return file.readBytes()
    }

    /**
     * Decrypt the wallet file object with the password using AES encryption.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param encryptedBytes The encrypted wallet file object
     * @param password The password to decrypt the wallet with
     * @param iv The initialization vector
     * @return the decrypted wallet file object
     */
    private fun decryptWallet(encryptedBytes: ByteArray, password: String, iv: ByteArray): ByteArray {
        logger.debug("Decrypting wallet...")

        // generate a 256-bit AES key from the password
        val keySpec = SecretKeySpec(password.toByteArray(), "AES")

        // create cipher object for decryption
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val gcmSpec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec)

        // decrypt encrypted bytes using AES encryption
        return cipher.doFinal(encryptedBytes)
    }

    /**
     * Serialize the wallet file object to a byte array.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param walletFile the wallet file object
     * @return the serialized wallet file object
     */
    private fun serialize(walletFile: WalletFile): ByteArray {
        val bos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(bos)
        oos.writeObject(walletFile)
        oos.flush()
        return bos.toByteArray()
    }

    companion object {

        /**
         * Encrypt the wallet file object with the password using AES encryption.
         *
         * @author Marcus Cvjeticanin
         * @since 0.2.0
         * @param plaintext the wallet file object
         * @param password the password
         * @param algorithm the encryption algorithm
         * @return ciphertext
         */
        fun encrypt(plaintext: ByteArray, password: String, algorithm: String): ByteArray {
            val passwordBytes = password.toByteArray(Charsets.UTF_8)
            val passwordSpec = SecretKeySpec(passwordBytes, algorithm.split("/")[0])
            val cipher = Cipher.getInstance(algorithm)
            cipher.init(Cipher.ENCRYPT_MODE, passwordSpec)
            return cipher.doFinal(plaintext)
        }
    }

}