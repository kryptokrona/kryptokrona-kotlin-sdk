// Copyright (c) 2022-2022, The Kryptokrona Project
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

package org.kryptokrona.sdk.wallet;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.Setter;
import org.kryptokrona.sdk.crypto.KeyPair;
import org.kryptokrona.sdk.model.util.TxInputAndOwner;
import org.kryptokrona.sdk.model.util.UnconfirmedInput;
import org.kryptokrona.sdk.transaction.TransactionInput;

import java.util.List;
import java.util.Map;

/**
 * SubWallet.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class SubWallet {

	/**
	 * A vector of the stored transaction input data, to be used for
	 * sending transactions later.
	 */
	private List<TransactionInput> unspentInputs;

	/**
	 * Inputs which have been used in a transaction, and are waiting to
	 * either be put into a block, or return to our wallet.
	 */
	private List<TransactionInput> lockedInputs;

	/**
	 * Inputs which have been spent in a transaction.
	 */
	private List<TransactionInput> spentInputs;

	/**
	 * Inputs which have come in from a transaction we sent - either from
	 * change or from sending to ourself - we use this to display unlocked
	 * balance correctly.
	 */
	private List<UnconfirmedInput> unconfirmedIncomingAmounts;

	/**
	 * This subwallet's public and private spend key.
	 */
	private KeyPair spendKeys;

	/**
	 * The timestamp to begin syncing the wallet at
	 * (usually creation time or zero).
	 */
	private long syncStartTimestamp;

	/**
	 * The height to begin syncing the wallet at.
	 */
	private long syncStartHeight;

	/**
	 * This subwallet's public address.
	 */
	private String address;

	/**
	 * The wallet has one 'main' address which we will use by default
	 * when treating it as a single user wallet.
	 */
	private boolean primaryAddress;

	public SubWallet(
		String address, long scanHeight, long timestamp, KeyPair spendKeys, boolean primaryAddress
	) {
		this.address = address;
		this.syncStartHeight = scanHeight;
		this.syncStartTimestamp = timestamp;
		this.spendKeys = spendKeys;
		this.primaryAddress = primaryAddress;
	}

	public void pruneSpentInputs(long pruneHeight) {

	}

	public void reset(long scanHeight, long scanTimestamp) {

	}

	public void markInputAsSpent(String keyImage, long spendHeight) {

	}

	public void markInputAsLocked(String keyImage, String transactionHash) {

	}

	public void removeCancelledTransaction(String transactionHash) {

	}

	public List<String> removeForkedTransactions(long forkHeight) {
		return null;
	}

	public void convertSyncTimestampToHeight(long startTimestamp, long startHeight) {

	}

	public List<String> getKeyImages() {
		return null;
	}

	public Observable<Map<String, String>> getTxInputKeyImage(String derivation, long outputIndex) {
		return Observable.empty();
	}

	public Map<Double, Double> getBalance(long currentHeight) {
		return null;
	}

	public long getUnconfirmedChange() {
		return 0;
	}

	public boolean haveSpendableInput(TransactionInput transactionInput, long currentHeight) {
		return false;
	}

	public List<TxInputAndOwner> getSpendableInputs(long currentHeight) {
		return null;
	}

	public void storeTransactionInput(TransactionInput transactionInput, boolean visViewWallet) {

	}

	public void storeUnconfirmedIncomingInput(UnconfirmedInput unconfirmedInput) {

	}
}
