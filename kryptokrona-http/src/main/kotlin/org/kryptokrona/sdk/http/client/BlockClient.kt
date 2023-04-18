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
import io.ktor.client.network.sockets.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.kryptokrona.sdk.http.common.HttpClient.client
import org.kryptokrona.sdk.http.common.get
import org.kryptokrona.sdk.http.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.http.model.response.blockdetail.BlockDetail
import org.kryptokrona.sdk.http.model.response.blocksdetail.BlocksDetails
import org.kryptokrona.sdk.http.model.response.blocksdetail.BlocksDetailsHashes
import org.kryptokrona.sdk.http.model.response.blocksdetail.BlocksHashesTimestamp
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory
import java.net.http.HttpTimeoutException

/**
 * Block client
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 * @param node The node that the wallet service is connected to.
 */
class BlockClient(private val node: Node) {

    private val logger = LoggerFactory.getLogger("BlockClient")

    /**
     * Get block details by hash
     *
     * @param blockDetailsByHeightRequest The block details by height request
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @return BlockDetail
     */
    suspend fun getBlockDetailsByHeight(blockDetailsByHeightRequest: BlockDetailsByHeightRequest): BlockDetail? {
        val jsonBody = Json.encodeToString(blockDetailsByHeightRequest)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_block_details_by_height")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_block_details_by_height")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<BlockDetail>()
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting block details by height. Could not reach the server.", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @return BlocksDetails
     */
    suspend fun getBlocksDetailsByHeights(): BlocksDetails? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_blocks_details_by_heights")
                        .body<BlocksDetails>()
                }

                return get("http://${node.hostName}:${node.port}/get_blocks_details_by_heights")
                    .body<BlocksDetails>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting blocks details by height. Could not reach the server.", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @return BlocksDetailsHashes
     */
    suspend fun getBlocksDetailsByHashes(): BlocksDetailsHashes? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_blocks_details_by_hashes")
                        .body<BlocksDetailsHashes>()
                }

                return get("http://${node.hostName}:${node.port}/get_blocks_details_by_hashes")
                    .body<BlocksDetailsHashes>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting blocks details by hashes. Could not reach the server.", e)
        }

        return null
    }

    /**
     * Get blocks hashes by timestamps
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @return BlocksHashesTimestamp
     */
    suspend fun getBlocksHashesByTimestamps(): BlocksHashesTimestamp? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_blocks_hashes_by_timestamps")
                        .body<BlocksHashesTimestamp>()
                }

                return get("http://${node.hostName}:${node.port}/get_blocks_hashes_by_timestamps")
                    .body<BlocksHashesTimestamp>()
            }
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting blocks details by hashes. Could not reach the server.", e)
        }

        return null
    }
}
