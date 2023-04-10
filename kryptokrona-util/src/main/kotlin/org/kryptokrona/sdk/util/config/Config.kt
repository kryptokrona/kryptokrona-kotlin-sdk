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

import java.util.*

/**
 * Config is a singleton object that holds configuration values for the library.
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
object Config {

    // TODO: should we be able to change these values before the app starts?

    /**
     * The amount of decimal places.
     */
    const val DECIMAL_PLACES = 5

    /**
     * The address prefix Kryptokrona uses - you can find this in CryptoNoteConfig.h.
     */
    const val ADDRESS_PREFIX: Long = 2239254

    /**
     * Request timeout for daemon operations in milliseconds.
     */
    const val REQUEST_TIMEOUT = (10 * 1000).toLong()

    /**
     * The block time of Kryptokrona, in seconds.
     */
    const val BLOCK_TARGET_TIME: Long = 90

    /**
     * How often to process blocks, in millseconds.
     */
    const val SYNC_THREAD_INTERVAL: Long = 5_00

    /**
     * How often to get the node info.
     */
    const val NODE_UPDATE_INTERVAL = (5 * 1000).toLong()

    /**
     * How often to check on locked transactions.
     */
    const val LOCKED_TRANSACTIONS_CHECK_INTERVAL = (30 * 1000).toLong()

    /**
     * The amount of blocks to process per 'tick' of the mainloop. Note: too
     * high a value will cause the event loop to be blocked, and your interaction
     * to be laggy.
     */
    const val BLOCKS_PER_TICK: Long = 1

    /**
     * Kryptokrona 'ticker'
     */
    const val TICKER = "XKR"

    /**
     * Most people haven't mined any blocks, so lets not waste time scanning
     * them.
     */
    const val SCAN_COINBASE_TRANSACTIONS = false

    /**
     * The minimum fee allowed for transactions, in ATOMIC units.
     */
    const val MINIMUM_FEE: Long = 10

    /**
     * Fee per byte is rounded up in chunks. This helps makes estimates
     * more accurate. It's suggested to make this a power of two, to relate
     * to the underlying storage cost / page sizes for storing a transaction.
     */
    const val FEE_PER_BYTE_CHUNK_SIZE = 256

    /**
     * Fee to charge per byte of transaction. Will be applied in chunks, see
     * above. This value comes out to 1.953125. We use this value instead of
     * something like 2 because it makes for pretty resulting fees
     * - 5 XKR vs 5.12 XKR. You can read this as the fee per chunk
     * is 500 atomic units. The fee per byte is 500 / chunk size.
     */
    const val MINIMUM_FEE_PER_BYTE = 500.00 / FEE_PER_BYTE_CHUNK_SIZE

    /**
     * Mapping of height to mixin maximum and mixin minimum.
     */
    /*const val MIXIN_LIMITS: MixinLimits = MixinLimits(
        Arrays.asList( // Height: 440,000, minMixin: 1, maxMixin: 100, defaultMixin: 3
            MixinLimit(440000, 1, 100, 3),  // At height of 620000, static mixin of 7
            MixinLimit(620000, 7),  // At height of 800000, static mixin of 3
            MixinLimit(800000, 3),  // Height: 1,250,000, minMixin: 1, maxMixin: 100, defaultMixin: 5
            MixinLimit(1250000, 1, 5, 3)
        ), 3
    )*/

    /**
     * The length of a standard address for Kryptokrona.
     */
    const val STANDARD_ADDRESS_LENGTH: Long = 99

    /**
     * The length of an integrated address for Kryptokrona - It's the same as
     * a normal address, but there is a paymentID included in there - since
     * payment ID's are 64 chars, and base58 encoding is done by encoding
     * chunks of 8 chars at once into blocks of 11 chars, we can calculate
     * this automatically.
     */
    const val INTEGRATED_ADDRESS_LENGTH = (99 + 64 * 11 / 8).toLong()

    /**
     * The amount of memory to use storing downloaded blocks - 50MB
     */
    const val BLOCK_STORE_MEMORY_LIMIT = (1024 * 1024 * 50).toLong()

    /**
     * The amount of blocks to take from the daemon per request. Cannot take
     * more than 100.
     */
    const val BLOCKS_PER_DAEMON_REQUEST: Long = 100

    /**
     * The amount of seconds to permit not having fetched a block from the
     * daemon before emitting 'deadnode'. Note that this just means contacting
     * the daemon for data - if you are synced and it returns TopBlock - the
     * event will not be emitted.
     */
    const val MAX_LAST_FETCHED_BLOCK_INTERVAL = (60 * 3).toLong()

    /**
     * The amount of seconds to permit not having fetched a new network height
     * from the daemon before emitting 'deadnode'.
     */
    const val MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL = (60 * 3).toLong()

    /**
     * The amount of seconds to permit not having fetched a new local height
     * from the daemon before emitting 'deadnode'.
     */
    const val MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL = (60 * 3).toLong()

    /**
     * Allows setting a custom user agent string
     */
    val CUSTOM_USER_AGENT_STRING = String.format(
        "%s-sdk-%s", TICKER.lowercase(Locale.getDefault()), System.getProperty("sdk-version")
    )

}