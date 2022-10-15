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

package org.kryptokrona.sdk.crypto;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.transaction.*;
import org.kryptokrona.sdk.wallet.Address;

import java.util.List;

/**
 * CryptoNote.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public interface CryptoNote {

	Address address();

	Observable<Void> init();

	Observable<Void> fetchKeys();

	List<Long> absoluteToRelativeOffsets(List<Object> offsets);

	List<Long> relativeToAbsoluteOffsets(List<Object> offsets);

	Observable<String> generateKeyDerivation(String transactionPublicKey, String privateViewKey);

	Observable<KeyImage> generateKeyImage(
			String transactionPublicKey, String privateViewKey, String publicSpendKey,
			String privateSpendKey, long outputIndex
	);

	Observable<KeyImage> generateKeyImagePrimitive(
			String publicSpendKey, String privateSpendKey, long outputIndex,
			String derivation
	);

	Observable<String> privateKeyToPublicKey(String privateKey);

	Observable<Output> scanTransactionOutputs(
			String transactionPublicKey, List<Output> outputs, String privateViewKey,
			String publicSpendKey, String privateSpendKey, boolean generatePartial
	);

	Observable<List<Output>> isOurTransactionOutput(
			String transactionPublicKey, List<Output> outputs, String privateViewKey,
			String publicSpendKey, String privateSpendKey, boolean generatePartial
	);

	long calculateMinimumTransactionFee(long txSize);

	Observable<String> createIntegratedAddress(String address, String paymentId, long prefix);

	String formatMoney(long amount);

	Observable<List<GeneratedOutput>> generateTransactionOutputs(String address, long amount);

	Observable<String> signMessage(String message, String privateKey);

	Observable<Boolean> verifyMessageSignature(String message, String publicKey, String signature);

	Observable<Transaction> createTransaction(
			List<GeneratedOutput> outputs, List<Output> inputs, List<RandomOutput> randomOutputs,
			double mixin, double feeAmount, String paymentId, long unlockTime, Object extraData
	);

	Observable<TransactionPrepared> createTransactionStructure(
			List<GeneratedOutput> outputs, List<Output> inputs, List<RandomOutput> randomOutputs,
			double mixin, double feeAmount, String paymentId, long unlockTime, Object extraData
	);

	Observable<TransactionPrepared> prepareTransaction(
			List<GeneratedOutput> outputs, List<Output> inputs, List<RandomOutput> randomOutputs,
			double mixin, double feeAmount, String paymentId, long unlockTime, Object extraData,
			String randomKey
	);

	Observable<Transaction> completeTransaction(TransactionPrepared preparedTransaction, String privateSpendKey);
}
