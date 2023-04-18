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
import org.kryptokrona.sdk.http.common.HttpClient.client
import org.kryptokrona.sdk.http.model.response.node.Fee
import org.kryptokrona.sdk.http.model.response.node.Height
import org.kryptokrona.sdk.http.model.response.node.Info
import org.kryptokrona.sdk.http.model.response.node.Peers
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory
import java.net.http.HttpTimeoutException
import java.nio.channels.UnresolvedAddressException

/**
 * Node client
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 * @param node The node that the wallet service is connected to.
 */
class NodeClient(private val node: Node) {

    private val logger = LoggerFactory.getLogger("NodeClient")

    /**
     * Check if node is running
     *
     * @since 0.1.0
     * @return Boolean
     */
    suspend fun isNodeRunning(): Boolean {
        var isSuccess = false

        try {
            node.ssl.let {
                val protocol = if (it) "https" else "http"
                val url = "$protocol://${node.hostName}:${node.port}/info"
                val response = client.get(url)
                isSuccess = response.status.isSuccess()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting node information. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting node information. Could not resolve the address.", e)
        }

        return isSuccess
    }

    /**
     * Get node peers
     *
     * @since 0.1.0
     * @return Peers
     */
    suspend fun getNodeInfo(): Info? {
        var result: Info? = null

        try {
            node.ssl.let {
                val protocol = if (it) "https" else "http"
                val url = "$protocol://${node.hostName}:${node.port}/info"
                result = client.get(url).body<Info>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting node information. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting node information. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting node information. Could not parse the response.", e)
        }

        return result
    }

    /**
     * Get node height
     *
     * @since 0.1.0
     * @return Height
     */
    suspend fun getNodeHeight(): Height? {
        var result: Height? = null

        try {
            node.ssl.let {
                val protocol = if (it) "https" else "http"
                val url = "$protocol://${node.hostName}:${node.port}/height"
                result = client.get(url).body<Height>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting node height. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting node height. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting node height. Could not parse the response.", e)
        }

        return result
    }

    /**
     * Get node peers
     *
     * @since 0.1.0
     * @return Peers
     */
    suspend fun getNodePeers(): Peers? {
        var result: Peers? = null

        try {
            node.ssl.let {
                val protocol = if (it) "https" else "http"
                val url = "$protocol://${node.hostName}:${node.port}/peers"
                result = client.get(url).body<Peers>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting node peers. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting node peers. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting node peers. Could not parse the response.", e)
        }

        return result
    }

    /**
     * Get node fee
     *
     * @since 0.1.0
     * @return Fee
     */
    suspend fun getNodeFee(): Fee? {
        var result: Fee? = null

        try {
            node.ssl.let {
                val protocol = if (it) "https" else "http"
                val url = "$protocol://${node.hostName}:${node.port}/fee"
                result = client.get(url).body<Fee>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting node fee. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting node fee. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting node fee. Could not parse the response.", e)
        }

        return result
    }
}
