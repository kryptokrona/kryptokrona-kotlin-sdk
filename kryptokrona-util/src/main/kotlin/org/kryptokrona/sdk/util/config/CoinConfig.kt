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

package org.kryptokrona.sdk.util.config

/**
 * CoinConfig.java
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
object CoinConfig {

    /**
     * The version of the parent block that activates the coin's features.
     */
    private const val ACTIVATE_PARENT_BLOCK_VERSION: Long = 2

    /**
     * The number of decimal places in the coin's unit.
     */
    private const val COIN_UNIT_PLACES: Long = 5

    /**
     * The prefix used for coin addresses.
     */
    private const val ADDRESS_PREFIX: Long = 2239254

    /**
     * The number of iterations of the Keccak hashing algorithm.
     */
    private const val KECCAK_ITERATIONS: Long = 1

    /**
     * The default network fee for transactions.
     */
    private const val DEFAULT_NETWORK_FEE = 10.0

    /**
     * The minimum number of inputs required for a fusion transaction.
     */
    private const val FUSION_MIN_INPUT_COUNT: Long = 12

    /**
     * The minimum ratio of inputs to outputs required for a fusion transaction.
     */
    private const val FUSION_MIN_IN_OUT_COUNT_RATIO: Long = 4

    /**
     * The version of the mining block that activates the coin's features.
     */
    private const val MM_MINING_BLOCK_VERSION: Long = 2

    /**
     * The maximum amount that can be sent in a single transaction output.
     */
    private const val MAXIMUM_OUTPUT_AMOUNT = 100000000000L

    /**
     * The maximum number of outputs allowed per transaction.
     */
    private const val MAXIMUM_OUTPUTS_PER_TRANSACTION: Long = 90

    /**
     * The maximum size in bytes of extra data that can be included in a transaction.
     */
    private const val MAXIMUM_EXTRA_SIZE: Long = 1024

    /**
     * Whether or not to activate the fee-per-byte transaction feature.
     */
    private const val ACTIVATE_FEE_PER_BYTE_TRANSACTION = true

    /**
     * The fee per byte of transaction data.
     */
    private const val FEE_PER_BYTE = 1.953125

    /**
     * The chunk size in bytes for fee-per-byte transactions.
     */
    private const val FEE_PER_BYTE_CHUNK_SIZE = 256.0

    /**
     * The maximum size in bytes of a single transaction in the ledger.
     */
    private const val MAXIMUM_LEDGER_TRANSACTION_SIZE: Long = 38400

    /**
     * The maximum payload size in bytes for a single APDU (Application Protocol Data Unit) sent to the ledger.
     */
    private const val MAXIMUM_LEDGER_APDU_PAYLOAD_SIZE: Long = 480

    /**
     * The minimum version of the ledger required for compatibility.
     */
    private const val MINIMUM_LEDGER_VERSION = "1.2.0"

    /**
     * Whether or not to enable debugging on the ledger.
     */
    private const val LEDGER_DEBUG = false
}
