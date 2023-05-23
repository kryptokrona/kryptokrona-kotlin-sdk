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

import org.kryptokrona.sdk.node.client.OutputsClient
import org.kryptokrona.sdk.util.model.node.Node
import org.kryptokrona.sdk.util.model.output.GeneratedOutput
import org.kryptokrona.sdk.util.model.output.RandomOutput
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

        // get random outs
        val randomOuts = getRingParticipants(emptyList(), 0L)

        val numPregenerated = 0
        val numGeneratedOnDemand = 0

        // get ourOutputs

        logger.info("Generated key images for $numGeneratedOnDemand inputs, " +
                "used pre-generated key images for $numPregenerated inputs.")


    }

    suspend fun getRingParticipants(inputs: List<TxInputAndOwner>, mixin: Long)/*: List<RandomOutput>*/ {
        if (mixin == 0L) {
            logger.info("No mixin, skipping ring participant collection.")
            // return emptyList()
        }

        /* Request one more than needed, this way if we get our own output as
         *one of the mixin outs, we can skip it and still form the transaction
         */
        val requestedOuts: Long = mixin + 1
        val amounts: List<Long> = inputs.map { input -> input.input.amount }

        val outs = getRandomOutputsByAmount(amounts, requestedOuts)



    }

    private suspend fun getRandomOutputsByAmount(amounts: List<Long>, requestedOuts: Long) {
        val data = mutableListOf<Long>()

        val response = outputsClient.getRandomOuts() // change to post request and send amounts and requestOuts later

        response?.outs?.forEach { output ->
            data.add(output)
        }

        data.forEach { println(it) }

    }

}