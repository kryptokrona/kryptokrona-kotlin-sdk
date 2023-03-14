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

package org.kryptokrona.sdk.http.client

import io.ktor.client.call.*
import org.kryptokrona.sdk.util.node.Node
import org.kryptokrona.sdk.http.common.get
import org.kryptokrona.sdk.http.model.transaction.TransactionDetailsHashes
import org.kryptokrona.sdk.http.model.transaction.TransactionHashesPaymentId
import org.kryptokrona.sdk.http.model.transaction.Transactions
import org.kryptokrona.sdk.http.model.transaction.TransactionsStatus
import org.slf4j.LoggerFactory

/**
 * Transaction client
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class TransactionClient(private val node: Node) {

    private val logger = LoggerFactory.getLogger("TransactionClient")

    /**
     * Get transactions
     *
     * @return Transactions
     */
    suspend fun getTransactions(): Transactions? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/gettransactions").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/gettransactions").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting transactions", e)
        }

        return null
    }

    /**
     * Get transaction details by hashes
     *
     * @return TransactionDetailsHashes
     */
    suspend fun getTransactionDetailsByHashes(): TransactionDetailsHashes? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_transaction_details_by_hashes").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_transaction_details_by_hashes").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting transaction details by hashes", e)
        }

        return null
    }

    /**
     * Get transaction hashes by payment id
     *
     * @return TransactionHashesPaymentId
     */
    suspend fun getTransactionHashesByPaymentId(): TransactionHashesPaymentId? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_transaction_hashes_by_payment_id").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_transaction_hashes_by_payment_id").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting transaction hashes by payment id", e)
        }

        return null
    }

    /**
     * Get transactions status
     *
     * @return TransactionsStatus
     */
    suspend fun getTransactionsStatus(): TransactionsStatus? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_transactions_status").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_transactions_status").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting transaction status", e)
        }

        return null
    }

}