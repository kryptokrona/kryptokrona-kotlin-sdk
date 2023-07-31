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

package org.kryptokrona.sdk.service.client

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.service.common.HttpClient
import org.kryptokrona.sdk.service.model.Service
import org.kryptokrona.sdk.service.model.request.transaction.*
import org.kryptokrona.sdk.service.model.response.transaction.*
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Transaction Service Client
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param service The service to connect to.
 */
class TransactionServiceClient(private val service: Service) {

    private val logger = LoggerFactory.getLogger("TransactionServiceClient")

    /**
     * Get a list of transactions hashes.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param getTransactionHashesRequest The request.
     * @return The response.
     */
    suspend fun getTransactionHashes(
        getTransactionHashesRequest: GetTransactionHashesRequest
    ): GetTransactionHashesResponse? {
        val jsonBody = Json.encodeToString(getTransactionHashesRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<GetTransactionHashesResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting transaction hashes. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting transaction hashes. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting transaction hashes. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get a list of transactions.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param getTransactionsRequest The request.
     * @return The response.
     */
    suspend fun getTransactions(getTransactionsRequest: GetTransactionsRequest): GetTransactionsResponse? {
        val jsonBody = Json.encodeToString(getTransactionsRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<GetTransactionsResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting transactions. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting transactions. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting transactions. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get a list of unconfirmed transaction hashes.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param getUnconfirmedTransactionHashesRequest The request.
     * @return The response.
     */
    suspend fun getUnconfirmedTransactionHashes(
        getUnconfirmedTransactionHashesRequest: GetUnconfirmedTransactionHashesRequest
    ):
            GetUnconfirmedTransactionHashesResponse? {
        val jsonBody = Json.encodeToString(getUnconfirmedTransactionHashesRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<GetUnconfirmedTransactionHashesResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting unconfirmed transaction hashes. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting unconfirmed transaction hashes. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting unconfirmed transaction hashes. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get a transaction from the service.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param getTransactionRequest The request to send.
     * @return The response from the service.
     */
    suspend fun getTransaction(getTransactionRequest: GetTransactionRequest): GetTransactionResponse? {
        val jsonBody = Json.encodeToString(getTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<GetTransactionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting transaction. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting transaction. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting transaction. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Send a transaction to the blockchain.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param sendTransactionRequest The request to send.
     * @return The response.
     */
    suspend fun sendTransaction(sendTransactionRequest: SendTransactionRequest): SendTransactionResponse? {
        val jsonBody = Json.encodeToString(sendTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<SendTransactionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending transaction. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending transaction. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending transaction. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Creates a delayed transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param createDelayedTransactionRequest The request object.
     * @return The response object.
     */
    suspend fun createDelayedTransaction(
        createDelayedTransactionRequest: CreateDelayedTransactionRequest
    ): CreateDelayedTransactionResponse? {
        val jsonBody = Json.encodeToString(createDelayedTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<CreateDelayedTransactionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error creating delayed transaction. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error creating delayed transaction. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error creating delayed transaction. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get a list of transaction hashes for all delayed transactions.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param getDelayedTransactionHashesRequest The request object.
     * @return The response object.
     */
    suspend fun getDelayedTransactionHashes(getDelayedTransactionHashesRequest: GetDelayedTransactionHashesRequest):
            GetDelayedTransactionHashesResponse? {
        val jsonBody = Json.encodeToString(getDelayedTransactionHashesRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<GetDelayedTransactionHashesResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting delayed transaction hashes. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting delayed transaction hashes. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting delayed transaction hashes. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Deletes a delayed transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param deleteDelayedTransactionRequest The request object.
     * @return The response object.
     */
    suspend fun deleteDelayedTransaction(deleteDelayedTransactionRequest: DeleteDelayedTransactionRequest):
            DeleteDelayedTransactionResponse? {
        val jsonBody = Json.encodeToString(deleteDelayedTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<DeleteDelayedTransactionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error deleting delayed transaction. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error deleting delayed transaction. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error deleting delayed transaction. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Sends a delayed transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param sendDelayedTransactionRequest The request object.
     * @return The response object.
     */
    suspend fun sendDelayedTransaction(sendDelayedTransactionRequest: SendDelayedTransactionRequest):
            SendDelayedTransactionResponse? {
        val jsonBody = Json.encodeToString(sendDelayedTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<SendDelayedTransactionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending delayed transaction. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending delayed transaction. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending delayed transaction. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Send a fusion transaction to the service.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param sendFusionTransactionRequest The request object.
     * @return The response object.
     */
    suspend fun sendFusionTransaction(sendFusionTransactionRequest: SendFusionTransactionRequest):
            SendFusionTransactionResponse? {
        val jsonBody = Json.encodeToString(sendFusionTransactionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<SendFusionTransactionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error sending fusion transaction. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error sending fusion transaction. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error sending fusion transaction. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Estimate the number of outputs that can be optimized in a fusion transaction.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @param estimateFusionRequest The request object.
     * @return The response object.
     */
    suspend fun estimateFusion(estimateFusionRequest: EstimateFusionRequest): EstimateFusionResponse? {
        val jsonBody = Json.encodeToString(estimateFusionRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            service.ssl.let {
                if (it) {
                    url.takeFrom("https://${service.hostName}:${service.port}/json_rpc")
                } else {
                    url.takeFrom("http://${service.hostName}:${service.port}/json_rpc")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return HttpClient.client.post(builder).body<EstimateFusionResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error estimating fusion. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error estimating fusion. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error estimating fusion. Could not parse the response.", e)
        }

        return null
    }
}
