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

package org.kryptokrona.sdk.core.service

import kotlinx.coroutines.*
import org.kryptokrona.sdk.http.client.BlockClient
import org.kryptokrona.sdk.http.client.WalletClient
import org.kryptokrona.sdk.http.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.http.model.response.wallet.WalletSyncData
import org.kryptokrona.sdk.http.model.request.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.http.model.response.block.BlockDetailBlock
import org.kryptokrona.sdk.http.model.response.node.Info
import org.kryptokrona.sdk.http.model.response.wallet.WalletSyncDataItem
import org.kryptokrona.sdk.http.model.response.wallet.WalletSyncDataItemCoinbaseTransaction
import org.kryptokrona.sdk.util.config.Config
import org.kryptokrona.sdk.util.model.block.Block
import org.kryptokrona.sdk.util.model.block.TopBlock
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDateTime.now

/**
 * WalletService class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.1
 * @param node The node that the wallet service is connected to.
 */
class WalletService(node: Node) {

    private val logger = LoggerFactory.getLogger("WalletService")

    private val walletClient = WalletClient(node)

    private val blockClient = BlockClient(node)

    private val nodeService = NodeService(node)

    private var syncJob: Job = Job()

    private var nodeInfo: Info? = null

    private var startHeight: Long = 0

    private var walletHeight: Long = 0

    private var checkpoints: List<String> = mutableListOf()

    /**
     * Stored blocks for later processing
     */
    private var storedBlocks = mutableListOf<Block>()

    /**
     * Whether we are already downloading a chunk of blocks
     */
    private var fetchingBlocks: Boolean = false

    private var shouldSleep: Boolean = false

    private var lastDownloadedBlocks: LocalDateTime = now()

    fun getNodeInfo() = nodeInfo

    fun setStartHeight(height: Long) {
        startHeight = height
    }

    /**
     * Starts the sync process.
     */
    suspend fun startSync() = coroutineScope {
        logger.info("Starting sync process...")

        val blockDetails = blockClient.getBlockDetailsByHeight(BlockDetailsByHeightRequest(startHeight))
        val blockHeight = blockDetails?.block?.index
        logger.info("Block height: $blockHeight")

        // add the starting block to the checkpoints
        blockDetails?.block?.hash?.let { checkpoints += it }

        if (blockHeight != null) {
            walletHeight = blockHeight
        }
        logger.info("Start height: $startHeight")
        logger.info("Wallet height: $walletHeight")

        syncJob = launch {
            launch(Dispatchers.IO) {
                while(isActive) {
                    logger.info("Syncing blocks...")

                    nodeInfo?.height?.let {
                        logger.info("Node height inside nodeInfo?.height: $it")
                        if (walletHeight < it) {
                            val requestData = WalletSyncDataRequest(blockHashCheckpoints = checkpoints, startHeight = blockHeight)
                            val walletSyncData = getSyncData(requestData)

                            walletSyncData?.let { wsd ->
                                checkpoints += wsd.items.last().blockHash
                                logger.info("Checkpoint INSIDE: ${checkpoints.last()}")
                                logger.info("Fetched ${wsd.items.size} blocks")
                            }

                            walletSyncData?.items?.forEach { _ -> walletHeight++ }

                            logger.debug("Checkpoints size: ${checkpoints.size}")
                            logger.info("Wallet height: $walletHeight")
                        } else {
                            logger.info("Synced...")
                        }
                    }

                    logger.info("Checkpoint: ${checkpoints.last()}")
                    logger.info("Wallet height: $walletHeight")
                    delay(Config.SYNC_THREAD_INTERVAL)
                }
            }

            launch(Dispatchers.IO) {
                while(isActive) {
                    nodeInfo = nodeService.getNodeInfo()
                    logger.info("Node height: ${nodeInfo?.height}")
                    delay(Config.NODE_UPDATE_INTERVAL)
                }
            }
        }

        syncJob.children.forEach { it.join() }
    }

    /**
     * Stops the sync process.
     */
    suspend fun stopSync() = coroutineScope {
        syncJob.children.forEach { it.cancel() }

        logger.info("Stopping sync process...")
    }

    /**
     * Gets the wallet sync data.
     *
     * @return The wallet sync data.
     */
    private suspend fun getSyncData(walletSyncDataRequest: WalletSyncDataRequest): WalletSyncData? {
        logger.info("Getting wallet sync data...")
        fetchBlocks(25)
        return walletClient.getWalletSyncData(walletSyncDataRequest)
    }

    /**
     * Retrieve blockCount blocks from the internal store.
     * Does not remove them.
     */
    private suspend fun fetchBlocks(blockCount: Int): List<Block> {
        shouldSleep = false

        // Fetch more blocks if we haven't got any downloaded yet
        if (storedBlocks.isEmpty()) {
            if (!fetchingBlocks) {
                logger.info("No blocks stored, attempting to fetch more...")
            }

            val downloadedBlocks = downloadBlocks()
            logger.info("Downloaded blocks: $downloadedBlocks")

            if (downloadedBlocks) {
                lastDownloadedBlocks = now()
            }
        }

        return storedBlocks
    }

    private fun downloadBlocks(): Boolean {
        if (fetchingBlocks) {
            logger.info("Already fetching blocks, skipping...")
            return false
        }

        fetchingBlocks = true

        val localNodeBlockCount = nodeInfo?.height ?: 0

        logger.info("Local node block count: $localNodeBlockCount")
        logger.info("Current wallet height: $walletHeight")

        if (localNodeBlockCount < walletHeight) {
            fetchingBlocks = false
            return true
        }

        // val blockCheckPoints = getWalletSyncDataHashes()

        /*var blocks = mutableListOf<Block>()
        var topBlock: TopBlock? = null

        try {
            // topBlock = getWalletSyncData()
        } catch (e: Exception) {
            logger.error("Failed to get block from node: ${e.message}")
            this.fetchingBlocks = false

            *//*if (finishedFunc) {
                finishedFunc()
            }*//*

            return mapOf(Pair(false, true))
        }

        if (topBlock != null && blocks.size == 0) {
            *//*if (finishedFunc) {
                finishedFunc()
            }*//*

            *//* Synced, store the top block so sync status displays correctly if
               we are not scanning coinbase tx only blocks.
               Only store top block if we have finished processing stored
               blocks *//*
            if (storedBlocks.isEmpty()) {
                // this.storeTopBlock(topBlock)
                // synchronizationStatus.storeBlockHash(topBlock.height, topBlock.hash)
            }

            logger.info("Zero blocks received, assuming synced")

            *//*if (finishedFunc) {
                finishedFunc()
            }*//*

            fetchingBlocks = false

            return mapOf(Pair(true, true))*/

        return true
    }

}