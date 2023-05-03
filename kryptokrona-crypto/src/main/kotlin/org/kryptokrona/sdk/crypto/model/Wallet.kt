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

package org.kryptokrona.sdk.crypto.model

import kotlinx.serialization.Serializable

/**
 * Wallet model.
 *
 * We are using this model for saving to file.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
@Serializable
data class Wallet(

    /**
     * A list of the stored transaction input data, to be used for
     * sending transactions later.
     */
    val unspentInputs: List<TransactionInput> = emptyList(),

    /**
     * Inputs which have been used in a transaction, and are waiting to
     * either be put into a block, or return to our wallet.
     */
    val lockedInputs: List<TransactionInput> = emptyList(),

    /**
     * Inputs which have been spent in a transaction.
     */
    val spentInputs: List<TransactionInput> = emptyList(),

    /**
     * Inputs which have come in from a transaction we sent - either from
     * change or from sending to ourself - we use this to display unlocked
     * balance correctly.
     */
    val unconfirmedIncomingAmounts: List<UnconfirmedInput> = emptyList(),

    /**
     * This subwallet's public spend key.
     */
    val publicSpendKey: String,

    /**
     * The subwallet's private spend key (undefined if view wallet).
     */
    val privateSpendKey: String? = null,

    /**
     * The timestamp to begin syncing the wallet at
     * (usually creation time or zero).
     */
    val syncStartTimestamp: Long = 0,

    /**
     * The height to begin syncing the wallet at.
     */
    val syncStartHeight: Long = 0,

    /**
     * This subwallet's public address.
     */
    val address: String = "",

    /**
     * The wallet has one 'main' address which we will use by default
     * when treating it as a single user wallet.
     */
    val primaryAddress: Boolean = false
)
