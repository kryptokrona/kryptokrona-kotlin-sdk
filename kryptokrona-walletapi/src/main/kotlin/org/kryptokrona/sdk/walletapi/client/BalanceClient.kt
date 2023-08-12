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
import org.kryptokrona.sdk.walletapi.common.HttpClient
import org.kryptokrona.sdk.walletapi.model.WalletApi
import org.kryptokrona.sdk.walletapi.model.response.BalanceResponse
import org.kryptokrona.sdk.walletapi.model.response.BalancesResponse
import org.kryptokrona.sdk.walletapi.model.response.StatusResponse
import org.slf4j.LoggerFactory
import java.nio.channels.UnresolvedAddressException

/**
 * Balance client
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 * @param walletApi The wallet API to connect to.
 */
class BalanceClient(private val walletApi: WalletApi) {

    private val logger = LoggerFactory.getLogger("BalanceClient")

    /**
     * Balance of wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return BalanceResponse
     */
    suspend fun walletBalance(): BalanceResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/balance")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/balance")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<BalanceResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting wallet balance from Wallet API. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting wallet balance from Wallet API. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting wallet balance from Wallet API. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Balances of each address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return BalancesResponse
     */
    suspend fun walletBalances(): BalancesResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/balances")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/balances")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<BalancesResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting wallet balances from each address from Wallet API. " +
                    "Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting wallet balances from each address from Wallet API. " +
                    "Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting wallet balances from each address from Wallet API. " +
                    "Could not parse the response.", e)
        }

        return null
    }

    /**
     * Balance of specific wallet address.
     *
     * @author Marcus Cvjeticanin
     * @since 0.3.0
     * @return BalanceResponse
     */
    suspend fun walletBalanceForSpecificAddress(address: String): BalanceResponse? {
        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Get
            walletApi.ssl.let {
                if (it) {
                    url.takeFrom("https://${walletApi.hostName}:${walletApi.port}/balance/$address")
                } else {
                    url.takeFrom("http://${walletApi.hostName}:${walletApi.port}/balances/$address")
                }
            }
        }

        try {
            return HttpClient.client.get(builder).body<BalanceResponse>()
        } catch (e: HttpRequestTimeoutException) {
            logger.error("Error getting wallet balance from specific address from Wallet API. " +
                    "Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting wallet balances from specific address from Wallet API. " +
                    "Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting wallet balances from specific address from Wallet API. " +
                    "Could not parse the response.", e)
        }

        return null
    }
}
