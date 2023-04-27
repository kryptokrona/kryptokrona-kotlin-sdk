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
import org.kryptokrona.sdk.crypto.Crypto
import org.kryptokrona.sdk.crypto.WalletEncryption
import org.kryptokrona.sdk.crypto.exception.GenerateKeyDerivationException
import org.kryptokrona.sdk.crypto.getKeyImageFromOutput
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
        // TODO we need to generate these when creating a new wallet and save them somewhere
        val publicSpendKey = "45f6f692d8dc545deff096b048e94ee25acd7bf67fb49f7d83107f9969b9bc67"
        val privateViewKey = "4451358855fb52b2199db97b33b6d7d47ac2b4067ecdf5ed20bb32162543270a"

        // if we get negative values of the convertion in the byte array generated the keys are invalid
        val pubSpend = convertHexToBytes(publicSpendKey)
        val privView = convertHexToBytes(privateViewKey)
        val txPubKey = convertHexToBytes(transaction.txPublicKey)

        // TODO add this as a property to class so we can send this data to WalletEncryption
        // val inputs = mutableListOf<TransactionInput>()

        val derivation = ByteArray(BYTE_ARRAY_LENGTH)
        val success = crypto.generateKeyDerivation(txPubKey, privView, derivation)

        // since this is a fatal error we throw an exception, since the keys cannot be invalid
        success.takeIf { it != 0 } ?: throw GenerateKeyDerivationException("Keys are invalid.")

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

            // this is not spent yet, we just got it :)
            // val spendHeight = 0

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

        // create wallet file
        // TODO: not done, should add more properties here
        wallet = Wallet(
            publicSpendKey = "",
        )

        // create wallet encryption object from data class
        val walletEncryption = WalletEncryption(wallet!!)

        // encrypt the wallet with the password and save it to file
        walletEncryption.encryptToFile(fileName, password)
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

        val walletEncryption = WalletEncryption()
        wallet = walletEncryption.loadWallet(fileName, password)

        // we now have loaded the wallet file
        isWalletLoaded = true
    }
}
