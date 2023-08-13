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

package org.kryptokrona.sdk.walletapi.client

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.walletapi.common.HttpClient.client
import org.kryptokrona.sdk.walletapi.model.WalletApi
import org.kryptokrona.sdk.walletapi.model.request.ImportViewWalletRequest
import org.kryptokrona.sdk.walletapi.model.request.ImportWalletWithKeyRequest
import org.kryptokrona.sdk.walletapi.model.request.ImportWalletWithSeedRequest
import org.kryptokrona.sdk.walletapi.model.request.ResetWalletRequest
import org.kryptokrona.sdk.walletapi.model.response.StatusResponse
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Wallet client
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param walletApi The wallet API to connect to.
 */
class WalletClient(private val walletApi: WalletApi) {

    private val logger = LoggerFactory.getLogger("WalletClient")

    /**
     * Open a wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun openWallet(): StatusResponse? {
        val jsonBody = Json.encodeToString(walletApi)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/wallet/open")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/wallet/open")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error open wallet from Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error open wallet from Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error open wallet from Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Import wallet with key.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun importWalletWithKey(importWalletWithKeyRequest: ImportWalletWithKeyRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(importWalletWithKeyRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/wallet/import/key")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/wallet/import/key")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error importing wallet key to Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error importing wallet key to Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error importing wallet key to Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Import wallet with seed.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun importWalletWithSeed(importWalletWithSeedRequest: ImportWalletWithSeedRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(importWalletWithSeedRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/wallet/import/seed")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/wallet/import/seed")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error importing wallet seed to Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error importing wallet seed to Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error importing wallet seed to Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Import view wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun importViewWallet(importViewWalletRequest: ImportViewWalletRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(importViewWalletRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/wallet/import/view")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/wallet/import/view")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error importing view wallet to Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error importing view wallet to Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error importing view wallet to Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Create wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun createWallet(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/wallet/create")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/wallet/create")
                }
            }
        }

        try {
            return client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error creating wallet with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error creating wallet with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error creating wallet with Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Delete wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun deleteWallet(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Delete
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/wallet")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/wallet")
                }
            }
        }

        try {
            return client.delete(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error deleting wallet with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error deleting wallet with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error deleting wallet with Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Save wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun saveWallet(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Put
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/save")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/save")
                }
            }
        }

        try {
            return client.put(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error saving wallet with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error saving wallet with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error saving wallet with Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Reset wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun resetWallet(resetWalletRequest: ResetWalletRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(resetWalletRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Put
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/reset")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/reset")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.put(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error resetting wallet with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error resetting wallet with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error resetting wallet with Wallet API. Could not parse the response.", e)
        }

        return null
    }
}
