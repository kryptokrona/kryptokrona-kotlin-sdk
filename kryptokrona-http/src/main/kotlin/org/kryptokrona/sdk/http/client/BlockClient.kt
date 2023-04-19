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
import org.kryptokrona.sdk.http.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksDetailsByHashesRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksDetailsByHeightsRequest
import org.kryptokrona.sdk.http.model.request.block.BlocksHashesByTimestampsRequest
import org.kryptokrona.sdk.http.model.response.blockdetail.BlockDetailResponse
import org.kryptokrona.sdk.http.model.response.blocksdetails.BlocksDetailsResponse
import org.kryptokrona.sdk.http.model.response.blocksdetails.BlocksDetailsByHashesResponse
import org.kryptokrona.sdk.http.model.response.blocksdetails.BlocksHashesByTimestampsResponse
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory
import java.net.http.HttpTimeoutException
import java.nio.channels.UnresolvedAddressException

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
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param blockDetailsByHeight The block details by height
     * @return BlockDetail
     */
    suspend fun getBlockDetailsByHeight(blockDetailsByHeight: BlockDetailsByHeightRequest): BlockDetailResponse? {
        val jsonBody = Json.encodeToString(blockDetailsByHeight)

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
            return client.post(builder).body<BlockDetailResponse>()
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting block details by height. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting block details by height. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting block details by height. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get blocks details by heights
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param blocksDetailsByHeights The blocks details by heights
     * @return BlocksDetails
     */
    suspend fun getBlocksDetailsByHeights(blocksDetailsByHeights: BlocksDetailsByHeightsRequest):
            BlocksDetailsResponse? {
        val jsonBody = Json.encodeToString(blocksDetailsByHeights)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_blocks_details_by_heights")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_blocks_details_by_heights")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<BlocksDetailsResponse>()
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting blocks details by height. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting blocks details by height. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting blocks details by height. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get blocks details by hashes
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param blocksDetailsByHashes The blocks details by hashes
     * @return BlocksDetailsHashes
     */
    suspend fun getBlocksDetailsByHashes(blocksDetailsByHashes: BlocksDetailsByHashesRequest):
            BlocksDetailsByHashesResponse? {
        val jsonBody = Json.encodeToString(blocksDetailsByHashes)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_blocks_details_by_hashes")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_blocks_details_by_hashes")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<BlocksDetailsByHashesResponse>()
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting blocks details by hashes. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting blocks details by hashes. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting blocks details by hashes. Could not parse the response.", e)
        }

        return null
    }

    /**
     * Get blocks hashes by timestamps
     *
     * @author Marcus Cvjeticanin
     * @since 0.1.0
     * @param blocksHashesByTimestamps The blocks hashes by timestamps
     * @return BlocksHashesTimestamp
     */
    suspend fun getBlocksHashesByTimestamps(blocksHashesByTimestamps: BlocksHashesByTimestampsRequest):
            BlocksHashesByTimestampsResponse? {
        val jsonBody = Json.encodeToString(blocksHashesByTimestamps)

        val builder = HttpRequestBuilder().apply {
            method = HttpMethod.Post
            node.ssl.let {
                if (it) {
                    url.takeFrom("https://${node.hostName}:${node.port}/get_blocks_hashes_by_timestamps")
                } else {
                    url.takeFrom("http://${node.hostName}:${node.port}/get_blocks_hashes_by_timestamps")
                }
            }
            contentType(ContentType.Application.Json)
            headers {
                append("Content-Length", jsonBody.length.toString())
            }
            setBody(jsonBody)
        }

        try {
            return client.post(builder).body<BlocksHashesByTimestampsResponse>()
        } catch (e: HttpTimeoutException) {
            logger.error("Error getting blocks details by hashes. Could not reach the server.", e)
        } catch (e: UnresolvedAddressException) {
            logger.error("Error getting blocks details by hashes. Could not resolve the address.", e)
        } catch (e: JsonConvertException) {
            logger.error("Error getting blocks details by hashes. Could not parse the response.", e)
        }

        return null
    }
}
