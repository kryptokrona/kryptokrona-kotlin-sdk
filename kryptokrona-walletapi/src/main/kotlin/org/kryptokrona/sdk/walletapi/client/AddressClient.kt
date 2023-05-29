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
import org.kryptokrona.sdk.walletapi.common.HttpClient
import org.kryptokrona.sdk.walletapi.model.WalletApi
import org.kryptokrona.sdk.walletapi.model.request.ImportAddressRequest
import org.kryptokrona.sdk.walletapi.model.request.ImportViewOnlyAddressRequest
import org.kryptokrona.sdk.walletapi.model.response.*
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Address client
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param walletApi The wallet API to connect to.
 */
class AddressClient(private val walletApi: WalletApi) {

    private val logger = LoggerFactory.getLogger("AddressClient")

    /**
     * Get the primary address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun primaryAddress(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/addresses/primary")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/addresses/primary")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting primary address from Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting primary address from Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting primary address from Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get a list of all addresses.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun addresses(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/addresses")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/addresses")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting all addresses from Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting all addresses from Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting all addresses from Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Create a random address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return RandomAddressResponse
     */
    suspend fun createRandomAddress(): RandomAddressResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/addresses/create")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/addresses/create")
                }
            }
        }

        try {
            return HttpClient.client.post(builder).body<RandomAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error creating a random address with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error creating a random address with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error creating a random address with Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Import an address with secret spend key.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return ImportAddressResponse
     */
    suspend fun importAddress(importAddressRequest: ImportAddressRequest): ImportAddressResponse? {
        val jsonBody = Json.encodeToString(importAddressRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/addresses/import")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/addresses/import")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<ImportAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error importing address with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error importing address with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error importing address with Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Import a view only address with a public spend key.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return ImportViewOnlyAddressResponse
     */
    suspend fun importViewOnlyAddress(
        importViewOnlyAddressRequest: ImportViewOnlyAddressRequest): ImportViewOnlyAddressResponse? {
        val jsonBody = Json.encodeToString(importViewOnlyAddressRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/addresses/import/view")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/addresses/import/view")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<ImportViewOnlyAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error importing view only address with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error importing view only address with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error importing view only address with Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Delete a given address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun deleteAddress(address: String): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Delete
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/addresses/$address")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/addresses/$address")
                }
            }
        }

        try {
            return HttpClient.client.delete(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error deleting address with Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error deleting address with Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error deleting address with Wallet API. Could not parse the response.", e)
        }

        return null
    }
}
