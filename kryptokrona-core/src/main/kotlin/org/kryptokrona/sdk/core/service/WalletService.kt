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
import kotlinx.coroutines.flow.*
import org.kryptokrona.sdk.core.config.Config
import org.kryptokrona.sdk.core.node.Node
import org.slf4j.LoggerFactory

/**
 * WalletService class.
 *
 * @param node The node that the wallet service is connected to.
 */
class WalletService(node: Node) {

    private val logger = LoggerFactory.getLogger("WalletService")

    private var syncJob: Job = Job()

    suspend fun startSync() = coroutineScope {
        logger.info("Starting sync process...")

        // sync()
        // updateNodeInfo()
        // checkLockedTransactions()

        syncJob = launch {
            launch {
            (0..20)
                .asSequence()
                .asFlow()
                .onEach {
                    delay(Config.SYNC_THREAD_INTERVAL)
                    println("Process block: ${it}")
                }
                .cancellable()
                .catch {e -> println("$e") }
                .collect { }
            }

            launch {
                (0..20)
                    .asSequence()
                    .asFlow()
                    .onEach {
                        delay(Config.NODE_UPDATE_INTERVAL)
                        println("Get node info: ${it}")
                    }
                    .cancellable()
                    .catch {e -> println("$e") }
                    .collect { }
            }

            launch {
                (0..20)
                    .asSequence()
                    .asFlow()
                    .onEach {
                        delay(Config.LOCKED_TRANSACTIONS_CHECK_INTERVAL)
                        println("Check locked transactions: ${it}")
                    }
                    .cancellable()
                    .catch {e -> println("$e") }
                    .collect { }
            }
        }

        syncJob.children.forEach { it.join() }
    }

    suspend fun stopSync() = coroutineScope {
        syncJob.cancelChildren()

        logger.info("Stopping sync process...")
    }
}