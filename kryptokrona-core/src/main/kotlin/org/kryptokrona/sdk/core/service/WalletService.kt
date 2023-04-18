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
import org.kryptokrona.sdk.crypto.Crypto
import org.kryptokrona.sdk.crypto.getKeyImageFromOutput
import org.kryptokrona.sdk.crypto.model.TransactionInput
import org.kryptokrona.sdk.crypto.util.convertHexToBytes
import org.kryptokrona.sdk.http.client.BlockClient
import org.kryptokrona.sdk.http.client.WalletClient
import org.kryptokrona.sdk.http.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.http.model.request.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.http.model.response.node.Info
import org.kryptokrona.sdk.http.model.response.walletsyncdata.Block
import org.kryptokrona.sdk.http.model.response.walletsyncdata.Transaction
import org.kryptokrona.sdk.http.model.response.walletsyncdata.WalletSyncData
import org.kryptokrona.sdk.util.config.Config
import org.kryptokrona.sdk.util.model.node.Node
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

    /**
     * The crypto external library from C code.
     */
    private val crypto = Crypto()

    /**
     * The wallet client, used for sending requests to a node.
     */
    private val walletClient = WalletClient(node)

    /**
     * The block client, used for sending requests to a node.
     */
    private val blockClient = BlockClient(node)

    /**
     * The node service, used for getting information of the node.
     */
    private val nodeService = NodeService(node)

    /**
     * The sync job for the wallet sync process.
     */
    private var syncJob: Job = Job()

    /**
     * The node info we get from node service.
     */
    private var nodeInfo: Info? = null

    /**
     * If we don't set the start height, we will start from the genesis block.
     */
    private var startHeight: Long = 0

    /**
     * The current height of the wallet. If we set a start height, we will start from that height.
     */
    private var walletHeight: Long = 0

    /**
     * Checkpoints for the wallet sync process.
     */
    private var checkpoints: MutableList<String> = mutableListOf()

    /**
     * Stored blocks for later processing
     */
    private var storedBlocks = mutableListOf<Block>()

    fun getNodeInfo() = nodeInfo

    fun getStoredBlocks() = storedBlocks

    fun setStartHeight(height: Long) {
        startHeight = height
    }

    /**
     * Starts the sync process.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     */
    suspend fun startSync() = coroutineScope {
        logger.info("Starting sync process...")
        walletHeight = startHeight

        // get the first hash for the checkpoints
        val blockDetails = blockClient.getBlockDetailsByHeight(BlockDetailsByHeightRequest(startHeight))
        blockDetails?.block?.hash?.let { checkpoints.plusAssign(it) }

        var lastCheckPoint = listOf(checkpoints.last())
        val requestData = WalletSyncDataRequest(blockHashCheckpoints = lastCheckPoint)
        val walletSyncData = getSyncData(requestData)
        walletSyncData.let { wsd ->
            if (wsd != null) {
                checkpoints.plusAssign(wsd.items.last().blockHash)
                walletHeight += wsd.items.size.toLong()
            }
        }

        syncJob = launch {
            launch(Dispatchers.IO) {
                while(isActive) {
                    logger.debug("Syncing blocks...")

                    nodeInfo?.height?.let {
                        if (walletHeight < it) {
                            lastCheckPoint = listOf(checkpoints.last())
                            val data = WalletSyncDataRequest(blockHashCheckpoints = lastCheckPoint)
                            val syncData = getSyncData(data)

                            syncData?.let { sd ->
                                if (sd.items.isNotEmpty()) {
                                    checkpoints.plusAssign(sd.items.last().blockHash)
                                    checkpoints = checkpoints.distinct().toMutableList() // removing duplicates
                                    walletHeight += sd.items.size.toLong()

                                    // add new blocks to stored blocks
                                    sd.items.forEach { block -> storedBlocks.add(block) }
                                    logger.info("Fetched ${sd.items.size} block(s)")
                                }
                            }

                            processBlocks()

                            logger.info("Wallet height: $walletHeight")
                        }
                    }

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
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     */
    suspend fun stopSync() = coroutineScope {
        syncJob.children.forEach { it.cancel() }

        logger.info("Stopping sync process...")
    }

    /**
     * Gets the wallet sync data.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @return The wallet sync data.
     */
    private suspend fun getSyncData(walletSyncDataRequest: WalletSyncDataRequest): WalletSyncData? {
        logger.debug("Getting wallet sync data...")
        return walletClient.getWalletSyncData(walletSyncDataRequest)
    }

    /**
     * Processes the stored blocks and checks the transactions
     * for outputs that belong to the wallet. Removes the processed
     * blocks from the storedBlocks list when done.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     */
    private fun processBlocks() {
        logger.info("Processing blocks...")

        val blocksToRemove = mutableListOf<Block>()

        storedBlocks.forEach { block ->
            block.transactions.forEach { transaction ->
                checkTransactionOutputs(transaction, block.blockHeight)
            }

            // update wallet scan height
            walletHeight = block.blockHeight

            // remove the checked block from storedBlocks
            blocksToRemove.add(block)
        }

        storedBlocks.removeAll(blocksToRemove)
    }

    /**
     * Checks the transaction outputs for outputs that belong to the wallet
     * and adds them to the wallet.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param transaction The transaction to check.
     * @param blockHeight The block height of the transaction.
     */
    private fun checkTransactionOutputs(transaction: Transaction, blockHeight: Long) {
        val publicSpendKey = "cde60afedba1e88a9c7e8b28cc038ee018d5a24a1a239cdcb8d32506a594f3cb"
        val privateViewKey = "8f066e33d45a0205b772f47b5a5d66f5b5e08fc329c45fc5f2a15a998ad0d4b4"

        val pubSpend = convertHexToBytes(publicSpendKey)
        val privView = convertHexToBytes(privateViewKey)
        val txPubKey = convertHexToBytes(transaction.txPublicKey)

        // val inputs = mutableListOf<TransactionInput>()

        val derivation = ByteArray(32)
        crypto.generateKeyDerivation(txPubKey, privView, derivation)

        transaction.outputs.forEachIndexed { index, output ->
            val key = output.key
            val derivedKey = convertHexToBytes(key)
            val base = ByteArray(32)

            crypto.underivePublicKey(derivation, index.toLong(), derivedKey, base)

            // if the derived spend key does not match, the output key is not designated to us.
            // continue checking other outputs.
            if (!pubSpend.contentEquals(derivedKey)) {
                return@forEachIndexed
            }

            // this transaction contains outputs that belong to us.
            // create the key image and transaction input and save it
            val keyImage = getKeyImageFromOutput(derivation, index.toLong(), pubSpend)

            // this is not spent yet, we just got it :)
            val spendHeight = 0

            // construct our transaction input, there may be more inputs from this transactions
            val txInput = TransactionInput(
                amount = output.amount,
                blockHeight = blockHeight,
                keyImage = keyImage,
                txPubKey = transaction.txPublicKey,
                key = key,
                privateEphemeral = keyImage.privateSpendKey,
                txHash = transaction.hash,
                txIndex = index.toLong(),
                spendHeight = 0,
                unlockTime = transaction.unlockTime,
                globalIndex = 0
            )

            logger.info("Transaction found with hash: " + txInput.txHash)

            // TODO we add this input to its wallet
            // inputs.add(txInput)
        }
    }
}
