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
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.http.common.HttpClient.client
import org.kryptokrona.sdk.http.model.request.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.http.model.response.walletsyncdata.WalletSyncData
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory
import java.net.http.HttpTimeoutException
import java.nio.channels.UnresolvedAddressException

/**
 * Wallet client
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.1
 * @param node The node that the wallet service is connected to.
 */
class WalletClient(private val node: Node) {

    private val logger = LoggerFactory.getLogger("WalletClient")

    /**
     * Get wallet sync data
     *
     * @param walletSyncData The wallet sync data request
     * @since 0.1.0
     * @return Transactions
     */
    suspend fun getWalletSyncData(walletSyncData: WalletSyncDataRequest): WalletSyncData? {
        val jsonBody = Json.encodeToString(walletSyncData)
        var result: WalletSyncData? = null

        try {
            node.ssl.let {
                val protocol = if (it) "https" else "http"
                val url = "$protocol://${node.hostName}:${node.port}/getwalletsyncdata"
                result = client.post(url) {
                    contentType(ContentType.Application.Json)
                    headers {
                        append("Content-Length", jsonBody.length.toString())
                    }
                    setBody(jsonBody)
                }.body<WalletSyncData>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting wallet sync data. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting wallet sync data. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting wallet sync data. Could not convert the response.", e)
        }

        return result
    }
}
