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

package org.kryptokrona.sdk.daemon;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.exception.daemon.DaemonOfflineException;
import org.kryptokrona.sdk.exception.network.NetworkBlockCountException;
import org.kryptokrona.sdk.exception.node.NodeDeadException;
import org.kryptokrona.sdk.block.Block;
import org.kryptokrona.sdk.block.RawBlock;
import org.kryptokrona.sdk.block.TopBlock;
import org.kryptokrona.sdk.model.http.RandomOutputsByAmount;
import org.kryptokrona.sdk.model.http.WalletSyncData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Daemon {

	/**
	 * Open a Daemon connection.
	 *
	 * @throws IOException : If no connection
	 */
	void init() throws IOException, NetworkBlockCountException, NodeDeadException, DaemonOfflineException;

	/**
	 * Update the daemon info.
	 *
	 * @return Observable
	 */
	Observable<Void> updateDaemonInfo() throws IOException, NodeDeadException;

	/**
	 * Update the fee address and amount.
	 *
	 * @return Observable
	 */
	Observable<Void> updateFeeInfo() throws IOException;

	/**
	 * Gets blocks from the daemon. Blocks are returned starting from the last
	 * known block hash (if higher than the startHeight/startTimestamp).
	 *
	 * @param walletSyncData : WalletSyncData
	 * @return Observable
	 */
	Observable<Map<List<Block>, TopBlock>> getWalletSyncData(WalletSyncData walletSyncData) throws IOException;

	/**
	 * Returns a mapping of transaction hashes to global indexes.
	 * <p>
	 * Get global indexes for the transactions in the range
	 * [startHeight, endHeight]
	 *
	 * @param startHeight : List
	 * @param endHeight   : int
	 * @return Observable
	 */
	Observable<Map<String, Integer>> getGlobalIndexesForRange(int startHeight, int endHeight) throws IOException;

	/**
	 * Returns all cancelled transactions.
	 *
	 * @param transactionHashes : List
	 * @return Observable
	 */
	Observable<List<String>> getCancelledTransactions(List<String> transactionHashes);

	/**
	 * Gets random outputs for the given amounts. requestedOuts per. Usually mixin+1.
	 * <p>
	 * Returns an array of amounts to global indexes and keys. There
	 * should be requestedOuts indexes if the daemon fully fulfilled
	 * our request.
	 *
	 * @param randomOutputsByAmount : RandomOutputsByAmount
	 * @return Observable
	 */
	Observable<List<Integer>> getRandomOutputsByAmount(RandomOutputsByAmount randomOutputsByAmount);

	/**
	 * Sending a transaction.
	 *
	 * @param rawTransaction : String
	 * @return Observable
	 */
	Observable<Map<Boolean, String>> sendTransaction(String rawTransaction) throws IOException;

	/**
	 * Convert raw blocks to blocks.
	 *
	 * @param rawBlocks : List
	 * @return Observable
	 */
	Observable<List<Block>> rawBlocksToBlocks(List<RawBlock> rawBlocks);

	/**
	 * Make a GET request.
	 *
	 * @param param : String
	 * @return Observable
	 */
	Observable<String> getRequest(String param) throws IOException;

	/**
	 * Make a POST request.
	 *
	 * @return Observable
	 */
	Observable<String> postRequest(String param, Object obj) throws IOException;

	/**
	 * Make a GET request and checks if status code is 200.
	 *
	 * @return Observable
	 */
	Observable<Boolean> daemonReachable() throws IOException;
}
