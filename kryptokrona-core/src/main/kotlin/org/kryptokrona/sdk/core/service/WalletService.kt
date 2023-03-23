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
import org.kryptokrona.sdk.http.client.WalletClient
import org.kryptokrona.sdk.http.model.node.Info
import org.kryptokrona.sdk.http.model.wallet.WalletSyncData
import org.kryptokrona.sdk.util.config.Config
import org.kryptokrona.sdk.util.node.Node
import org.slf4j.LoggerFactory

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

    private val nodeService = NodeService(node)

    private var syncJob: Job = Job()

    private var walletSyncData: WalletSyncData? = null

    private var nodeInfo: Info? = null

    /**
     * Starts the sync process.
     */
    suspend fun startSync() = coroutineScope {
        logger.info("Starting sync process...")

        syncJob = launch {
            launch(Dispatchers.IO) {
                while(isActive) {
                    logger.info("Fetching blocks...")
                    walletSyncData = getSyncData()
                    walletSyncData.let { logger.info("Fetched ${it?.items?.size} blocks") }
                    delay(Config.SYNC_THREAD_INTERVAL)
                }
            }

            launch(Dispatchers.IO) {
                while(isActive) {
                    nodeInfo = nodeService.getNodeInfo()
                    delay(Config.NODE_UPDATE_INTERVAL)
                }
            }

            launch(Dispatchers.IO) {
                while(isActive) {
                    logger.info("Checking locked transactions...")
                    delay(Config.LOCKED_TRANSACTIONS_CHECK_INTERVAL)
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
    private suspend fun getSyncData(): WalletSyncData? {
        logger.info("Getting wallet sync data...")
        return walletClient.getWalletSyncData()
    }
}