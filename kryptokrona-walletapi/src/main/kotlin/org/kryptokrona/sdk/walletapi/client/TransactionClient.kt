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
import io.ktor.http.headers
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.walletapi.common.HttpClient
import org.kryptokrona.sdk.walletapi.model.WalletApi
import org.kryptokrona.sdk.walletapi.model.request.*
import org.kryptokrona.sdk.walletapi.model.response.StatusResponse
import org.kryptokrona.sdk.walletapi.model.response.TransactionsUnconfirmedWithAddressResponse
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Transaction client
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param walletApi The wallet API to connect to.
 */
class TransactionClient(private val walletApi: WalletApi) {

    private val logger = LoggerFactory.getLogger("TransactionClient")

    /**
     * Send transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun sendTransaction(sendTransactionRequest: SendTransactionRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(sendTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions/send/basic")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions/send/basic")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending basic transaction through Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending basic transaction through Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending basic transaction through Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Send advanced transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun sendTransactionAdvanced(sendTransactionAdvancedRequest: SendTransactionAdvancedRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(sendTransactionAdvancedRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions/send/advanced")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions/send/advanced")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending advanced transaction through Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending advanced transaction through Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending advanced transaction through Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Send fusion transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun sendTransactionFusion(sendTransactionFusionRequest: SendTransactionFusionRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(sendTransactionFusionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions/send/fusion/basic")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions/send/fusion/basic")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending fusion transaction through Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending fusion transaction through Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending fusion transaction through Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Send advanced fusion transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun sendTransactionFusionAdvanced(
        sendTransactionFusionAdvancedRequest: SendTransactionFusionAdvancedRequest): StatusResponse? {
        val jsonBody = Json.encodeToString(sendTransactionFusionAdvancedRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions/send/fusion/advanced")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions/send/fusion/advanced")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending advanced fusion transaction through Wallet API. " +
                    "Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending advanced fusion transaction through Wallet API. " +
                    "Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending advanced fusion transaction through Wallet API. " +
                    "Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get all transactions.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun transactions(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting all transactions from Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting all transactions from Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting all transactions from Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get all unconfirmed transactions.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return StatusResponse
     */
    suspend fun transactionsUnconfirmed(): StatusResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions/unconfirmed")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions/unconfirmed")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<StatusResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting all unconfirmed transactions from Wallet API. " +
                    "Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting all unconfirmed transactions from Wallet API. " +
                    "Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting all unconfirmed transactions from Wallet API. " +
                    "Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get all unconfirmed transactions with a given address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return TransactionsUnconfirmedWithAddressResponse
     */
    suspend fun transactionsUnconfirmedWithAddress(address: String): TransactionsUnconfirmedWithAddressResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/transactions/unconfirmed/$address")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/transactions/unconfirmed/$address")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<TransactionsUnconfirmedWithAddressResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting all unconfirmed transactions with a given address from Wallet API. " +
                    "Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting all unconfirmed transactions with a given address from Wallet API. " +
                    "Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting all unconfirmed transactions with a given address from Wallet API. " +
                    "Could not parse the response.", e)
        }

        return null
    }
}
