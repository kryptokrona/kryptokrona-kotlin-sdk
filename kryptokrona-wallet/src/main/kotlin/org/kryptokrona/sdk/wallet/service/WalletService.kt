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

package org.kryptokrona.sdk.wallet.service

import kotlinx.coroutines.*
import org.kryptokrona.sdk.crypto.*
import org.kryptokrona.sdk.crypto.exception.GenerateKeyDerivationException
import org.kryptokrona.sdk.crypto.model.TransactionInput
import org.kryptokrona.sdk.crypto.model.Wallet
import org.kryptokrona.sdk.crypto.util.convertHexToBytes
import org.kryptokrona.sdk.node.client.BlockClient
import org.kryptokrona.sdk.node.client.WalletClient
import org.kryptokrona.sdk.node.model.request.block.BlockDetailsByHeightRequest
import org.kryptokrona.sdk.node.model.request.wallet.WalletSyncDataRequest
import org.kryptokrona.sdk.node.model.response.node.InfoResponse
import org.kryptokrona.sdk.node.model.response.walletsyncdata.Block
import org.kryptokrona.sdk.node.model.response.walletsyncdata.Transaction
import org.kryptokrona.sdk.node.model.response.walletsyncdata.WalletSyncData
import org.kryptokrona.sdk.node.service.NodeService
import org.kryptokrona.sdk.util.config.Config
import org.kryptokrona.sdk.util.model.node.Node
import org.slf4j.LoggerFactory

private const val BYTE_ARRAY_LENGTH = 32 // length of the byte arrays used in the function

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
    private var nodeInfo: InfoResponse? = null

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

    /**
     * The wallet file, used for storing the wallet.
     */
    private var wallet: Wallet? = null

    /**
     * If the wallet is loaded.
     */
    private var isWalletLoaded: Boolean = false

    /**
     * If the sync process is canceled.
     */
    private var isSyncCanceled = false

    fun getNodeInfo() = nodeInfo

    fun getStoredBlocks() = storedBlocks

    fun getWallet() = wallet

    fun getIsWalletLoaded() = isWalletLoaded

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

                            if (processBlocks()) {
                                logger.info("Sync process canceled...")
                                return@launch
                            }

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
    fun stopSync() {
        isSyncCanceled = true
        syncJob.cancelChildren()
        syncJob.cancel()

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
    private fun processBlocks(): Boolean {
        logger.info("Processing blocks...")

        val blocksToRemove = mutableListOf<Block>()

        for (block in storedBlocks) {
            if (isSyncCanceled) break

            for (transaction in block.transactions) {
                if (isSyncCanceled) {
                    isSyncCanceled = true
                    break
                }

                checkTransactionOutputs(transaction, block.blockHeight)
            }

            if (!isSyncCanceled) {
                walletHeight = block.blockHeight
                blocksToRemove.add(block)
            }
        }

        if (isSyncCanceled) return true

        // remove all the processed blocks since we do not need them anymore
        storedBlocks.removeAll(blocksToRemove)

        return false
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
        assert(wallet != null)

        // the public spend key and private view key from the wallet
        val publicSpendKey = wallet?.publicSpendKey
        val privateViewKey = wallet?.privateViewKey

        // if we get negative values of the convertion in the byte array generated the keys are invalid
        val pubSpend = convertHexToBytes(publicSpendKey!!)
        val privView = convertHexToBytes(privateViewKey!!)
        val txPubKey = convertHexToBytes(transaction.txPublicKey)

        // the inputs we will collect from the transaction outputs
        val inputs = mutableListOf<TransactionInput>()

        val derivation = ByteArray(BYTE_ARRAY_LENGTH)
        val success = crypto.generateKeyDerivation(txPubKey, privView, derivation)

        // since this is a fatal error we throw an exception, since the keys cannot be invalid
        success.takeIf { it != 0 } ?: throw GenerateKeyDerivationException("Keys are invalid.")

        // check all the outputs of the transaction
        transaction.outputs.forEachIndexed { index, output ->
            val key = output.key
            val derivedKey = convertHexToBytes(key)
            val base = ByteArray(BYTE_ARRAY_LENGTH)

            crypto.underivePublicKey(derivation, index.toLong(), derivedKey, base)

            // if the underived spend key does not match, the output key is not designated to us.
            // continue checking other outputs.
            if (!pubSpend.contentEquals(derivedKey)) {
                return@forEachIndexed
            }

            // this transaction contains outputs that belong to us.
            // create the key image and transaction input and save it
            val keyImage = getKeyImageFromOutput(derivation, index.toLong(), pubSpend)

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

            // save the input
            inputs.add(txInput)
        }

        // adding all the inputs as unspent to the wallet
        wallet?.unspentInputs = wallet?.unspentInputs?.plus(inputs) ?: inputs
    }

    /**
     * Saves the wallet to a file.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param fileName The name of the file to save the wallet to.
     * @param password The password to encrypt the wallet with.
     */
    fun saveWalletToFile(fileName: String, password: String) {
        logger.debug("Saving wallet to file...")

        // generate keypairs
        val walletKeyPairs = generateKeyPairs()

        // generate address
        val address = generateAddress(walletKeyPairs.publicSpendKey, walletKeyPairs.publicViewKey)

        // create a wallet data class
        wallet = Wallet(
            publicSpendKey = walletKeyPairs.publicSpendKey,
            privateSpendKey = walletKeyPairs.privateSpendKey,
            publicViewKey = walletKeyPairs.publicViewKey,
            privateViewKey = walletKeyPairs.privateViewKey,
            address = address,
            syncStartHeight = startHeight,
            primaryAddress = true
        )

        // create wallet encryption object from data class
        val walletFileEncryption = WalletFileEncryption(wallet!!)

        // encrypt the wallet with the password and save it to file
        walletFileEncryption.encryptToFile(fileName, password)
    }

    /**
     * Loads the wallet from a file.
     *
     * @author Marcus Cvjeticanin
     * @since 0.2.0
     * @param fileName The name of the file to load the wallet from.
     * @param password The password to decrypt the wallet with.
     */
    fun loadWalletFromFile(fileName: String, password: String) {
        logger.debug("Loading wallet from file...")

        val walletFileEncryption = WalletFileEncryption()
        wallet = walletFileEncryption.loadWallet(fileName, password)
        isWalletLoaded = true
    }
}
