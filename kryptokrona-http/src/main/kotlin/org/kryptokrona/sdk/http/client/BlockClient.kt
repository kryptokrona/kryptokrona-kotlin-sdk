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
import org.kryptokrona.sdk.core.node.Node
import org.kryptokrona.sdk.http.common.get
import org.kryptokrona.sdk.http.model.block.*
import org.kryptokrona.sdk.http.model.queryblocks.QueryBlocks
import org.kryptokrona.sdk.http.model.queryblocks.QueryBlocksLite
import org.slf4j.LoggerFactory

/**
 * Block client
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class BlockClient(private val node: Node) {

    private val logger = LoggerFactory.getLogger("BlockClient")

    /**
     * Get block details by hash
     *
     * @return BlockDetailsHash
     */
    suspend fun getBlocks(): Blocks? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/getblocks").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/getblocks").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting blocks", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @return BlockDetailsHash
     */
    suspend fun getQueryBlocks(): QueryBlocks? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/queryblocks").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/queryblocks").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting query blocks", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @return BlockDetailsHash
     */
    suspend fun getQueryBlocksLite(): QueryBlocksLite? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/queryblockslite").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/queryblockslite").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting query blocks lite", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @return BlockDetailsHash
     */
    suspend fun getBlockDetailsByHeight(): BlockDetail? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_block_details_by_height").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_block_details_by_height").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting block details by height", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @return BlockDetailsHash
     */
    suspend fun getBlocksDetailsByHeights(): BlocksDetails? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_blocks_details_by_heights").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_blocks_details_by_heights").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting blocks details by height", e)
        }

        return null
    }

    /**
     * Get block details by hash
     *
     * @return BlockDetailsHash
     */
    suspend fun getBlocksDetailsByHashes(): BlocksDetailsHashes? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_blocks_details_by_hashes").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_blocks_details_by_hashes").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting blocks details by hashes", e)
        }

        return null
    }

    /**
     * Get blocks hashes by timestamps
     *
     * @return BlocksHashesTimestamp
     */
    suspend fun getBlocksHashesByTimestamps(): BlocksHashesTimestamp? {
        try {
            node.ssl.let {
                if (it) {
                    return get("https://${node.hostName}:${node.port}/get_blocks_hashes_by_timestamps").body()
                } else {
                    return get("http://${node.hostName}:${node.port}/get_blocks_hashes_by_timestamps").body()
                }
            }
        } catch (e: Exception) {
            logger.error("Error getting blocks details by hashes", e)
        }

        return null
    }

}