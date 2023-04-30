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

package org.kryptokrona.sdk.node.client

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.node.common.HttpClient.client
import org.kryptokrona.sdk.node.model.request.transaction.TransactionDetailsByHashesRequest
import org.kryptokrona.sdk.node.model.request.transaction.TransactionHashesPaymentIdRequest
import org.kryptokrona.sdk.node.model.request.transaction.TransactionsRequest
import org.kryptokrona.sdk.node.model.request.transaction.TransactionsStatusRequest
import org.kryptokrona.sdk.node.model.response.transaction.TransactionDetailsHashes
import org.kryptokrona.sdk.node.model.response.transaction.TransactionHashesPaymentId
import org.kryptokrona.sdk.node.model.response.transaction.Transactions
import org.kryptokrona.sdk.node.model.response.transaction.TransactionsStatus
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Transaction client
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 * @param node The node that the wallet service is connected to.
 */
class TransactionClient(private val node: Node) {

    private val logger = LoggerFactory.getLogger("TransactionClient")

    /**
     * Get transactions
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param transactions The transactions request
     * @return Transactions
     */
    suspend fun getTransactions(transactions: TransactionsRequest): Transactions? {
        val jsonBody = Json.encodeToString(transactions)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/gettransactions")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/gettransactions")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.request(builder).body<Transactions>()
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
     * Get transaction details by hashes
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param transactionDetailsByHashes The transaction details by hashes request
     * @return TransactionDetailsHashes
     */
    suspend fun getTransactionDetailsByHashes(transactionDetailsByHashes: TransactionDetailsByHashesRequest):
            TransactionDetailsHashes? {
        val jsonBody = Json.encodeToString(transactionDetailsByHashes)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_transaction_details_by_hashes")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_transaction_details_by_hashes")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.request(builder).body<TransactionDetailsHashes>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting transaction details by hashes. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting transaction details by hashes. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting transaction details by hashes. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get transaction hashes by payment id
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param transactionHashesPaymentId The transaction hashes payment id request
     * @return TransactionHashesPaymentId
     */
    suspend fun getTransactionHashesByPaymentId(transactionHashesPaymentId: TransactionHashesPaymentIdRequest):
            TransactionHashesPaymentId? {
        val jsonBody = Json.encodeToString(transactionHashesPaymentId)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_transaction_hashes_by_payment_id")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_transaction_hashes_by_payment_id")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<TransactionHashesPaymentId>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting transaction hashes by payment id. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting transaction hashes by payment id. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting transaction hashes by payment id. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get transactions status
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param transactionsStatus The transactions status request
     * @return TransactionsStatus
     */
    suspend fun getTransactionsStatus(transactionsStatus: TransactionsStatusRequest): TransactionsStatus? {
        val jsonBody = Json.encodeToString(transactionsStatus)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_transactions_status")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_transactions_status")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<TransactionsStatus>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting transaction status. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting transaction status. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting transaction status. Could not parse the response.", e)
        }

        return null
    }
}
