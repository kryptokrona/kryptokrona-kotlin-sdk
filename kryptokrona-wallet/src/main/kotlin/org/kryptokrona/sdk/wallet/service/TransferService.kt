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

import org.kryptokrona.sdk.crypto.generateKeyImage
import org.kryptokrona.sdk.crypto.util.convertHexToBytes
import org.kryptokrona.sdk.crypto.util.isHex64
import org.kryptokrona.sdk.node.client.OutputsClient
import org.kryptokrona.sdk.util.config.Config
import org.kryptokrona.sdk.util.model.input.GeneratedInput
import org.kryptokrona.sdk.util.model.input.InputKeys
import org.kryptokrona.sdk.util.model.node.Node
import org.kryptokrona.sdk.util.model.output.GeneratedOutput
import org.kryptokrona.sdk.util.model.output.Output
import org.kryptokrona.sdk.util.model.output.RandomOutput
import org.kryptokrona.sdk.util.model.transaction.PreparedTransaction
import org.kryptokrona.sdk.util.model.transaction.Transaction
import org.kryptokrona.sdk.util.model.transaction.TxInputAndOwner
import org.slf4j.LoggerFactory

/**
 * Transfer Service for handling transactions.
 *
 * @author Marcus Cvjeticanin
 * @since 0.3.0
 */
class TransferService(node: Node) {

    private val logger = LoggerFactory.getLogger("TransferService")

    private val outputsClient: OutputsClient = OutputsClient(node)

    // add subWallets: SubWallets, later as a parameter
    suspend fun makeTransaction(
        mixin: Long,
        fee: Double,
        paymentId: String,
        ourInputs: List<TxInputAndOwner>,
        destinations: List<GeneratedOutput>,
        extraData: String? = null
    ) {
        logger.info("Collecting ring participants...") // change to debug later

        ourInputs.sortedBy { input -> input.input.amount }

        // get random outs from node
        val randomOuts = getRingParticipants(ourInputs, mixin)

        var numPregenerated = 0
        var numGeneratedOnDemand = 0

        // go through our inputs

        val ourOutputs = ourInputs.map { input ->
            val generatedInput = input.input

            if (generatedInput.privateEphemeral.isEmpty() || !isHex64(generatedInput.privateEphemeral)) {
                val keyImage = generateKeyImage(
                    convertHexToBytes(generatedInput.transactionPublicKey),
                    convertHexToBytes("privateViewKeyHere"),
                    convertHexToBytes(input.publicSpendKey),
                    convertHexToBytes(input.privateSpendKey),
                    generatedInput.transactionIndex
                )

                generatedInput.privateEphemeral = keyImage.privateSpendKey

                numGeneratedOnDemand++
            } else {
                numPregenerated++
            }

            Output(
                amount = generatedInput.amount,
                globalIndex = generatedInput.globalOutputIndex,
                index = generatedInput.transactionIndex,
                input = GeneratedInput(
                    privateEphemeral = generatedInput.privateEphemeral,
                    publicEphemeral = "", // Required by compiler, not used in func
                    transactionKeys = InputKeys(
                        derivedKey = "",
                        outputIndex = 0,
                        publicKey = ""
                    )
                ),
                key = generatedInput.key,
                keyImage = generatedInput.keyImage
            )
        }

        logger.info("Generated key images for $numGeneratedOnDemand inputs, " +
                "used pre-generated key images for $numPregenerated inputs.")


        logger.info("Asynchronously creating transaction.")

        val tx = createTransaction(destinations, ourOutputs, randomOuts, mixin, fee, paymentId, extraData)

        logger.info("Transaction creation succeeded.")

        // return tx
        TODO()
    }

    private suspend fun createTransaction(
        outputs: List<GeneratedOutput>,
        inputs: List<Output>,
        randomOuts: List<RandomOutput>,
        mixin: Long,
        fee: Double? = null,
        paymentId: String? = null,
        unlockTime: Long? = null,
        extraData: String? = null
    ): Transaction {
        // add to Config later
        val feePerByte = true

        val prepared = createTransactionStructure()

        // val txPrefixHash = prepared.transaction.prefixHash()

        TODO()
    }

    private suspend fun createTransactionStructure(): PreparedTransaction {
        TODO()
    }

    private suspend fun getRingParticipants(inputs: List<TxInputAndOwner>, mixin: Long) : List<RandomOutput> {
        if (mixin == 0L) {
            logger.info("No mixin, skipping ring participant collection.")
            return emptyList()
        }

        /* Request one more than needed, this way if we get our own output as
         * one of the mixin outs, we can skip it and still form the transaction
         */
        val requestedOuts: Long = mixin + 1
        val amounts: List<Double> = inputs.map { input -> input.input.amount }

        val outs = getRandomOutputsByAmount(amounts, requestedOuts)

        if (outs.isEmpty()) {
            logger.error("Failed to get random outputs from node.")
        }

        amounts.forEach {
            val foundOutputs = outs.find { (outAmount, _) -> amount == outAmount }
        }

        TODO()
    }

    private suspend fun getRandomOutputsByAmount(amounts: List<Double>, requestedOuts: Long): List<RandomOutput> {
        val data = mutableListOf<Long>()

        val response = outputsClient.getRandomOuts() // change to post request and send amounts and requestOuts later

        response?.outs?.forEach { output ->
            data.add(output)
        }

        data.forEach { println(it) }

        TODO()
    }

}