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

package org.mjovanc.kryptokrona.crypto;

import io.reactivex.rxjava3.core.Observable;
import org.mjovanc.kryptokrona.transaction.*;
import org.mjovanc.kryptokrona.transaction.*;
import org.mjovanc.kryptokrona.wallet.Address;

import java.util.List;

public class CryptoNoteIml implements CryptoNote {

	@Override
	public Address address() {
		return null;
	}

	@Override
	public Observable<Void> init() {
		return null;
	}

	@Override
	public Observable<Void> fetchKeys() {
		return null;
	}

	@Override
	public List<Long> absoluteToRelativeOffsets(List<Object> offsets) {
		return null;
	}

	@Override
	public List<Long> relativeToAbsoluteOffsets(List<Object> offsets) {
		return null;
	}

	@Override
	public Observable<String> generateKeyDerivation(String transactionPublicKey, String privateViewKey) {
		return null;
	}

	@Override
	public Observable<KeyImage> generateKeyImage(String transactionPublicKey, String privateViewKey, String publicSpendKey, String privateSpendKey, long outputIndex) {
		return null;
	}

	@Override
	public Observable<KeyImage> generateKeyImagePrimitive(String publicSpendKey, String privateSpendKey, long outputIndex, String derivation) {
		return null;
	}

	@Override
	public Observable<String> privateKeyToPublicKey(String privateKey) {
		return null;
	}

	@Override
	public Observable<Output> scanTransactionOutputs(String transactionPublicKey, List<Output> outputs, String privateViewKey, String publicSpendKey, String privateSpendKey, boolean generatePartial) {
		return null;
	}

	@Override
	public Observable<List<Output>> isOurTransactionOutput(String transactionPublicKey, List<Output> outputs, String privateViewKey, String publicSpendKey, String privateSpendKey, boolean generatePartial) {
		return null;
	}

	@Override
	public long calculateMinimumTransactionFee(long txSize) {
		return 0;
	}

	@Override
	public Observable<String> createIntegratedAddress(String address, String paymentId, long prefix) {
		return null;
	}

	@Override
	public String formatMoney(long amount) {
		return null;
	}

	@Override
	public Observable<List<GeneratedOutput>> generateTransactionOutputs(String address, long amount) {
		return null;
	}

	@Override
	public Observable<String> signMessage(String message, String privateKey) {
		return null;
	}

	@Override
	public Observable<Boolean> verifyMessageSignature(String message, String publicKey, String signature) {
		return null;
	}

	@Override
	public Observable<Transaction> createTransaction(List<GeneratedOutput> outputs, List<Output> inputs, List<RandomOutput> randomOutputs, double mixin, double feeAmount, String paymentId, long unlockTime, Object extraData) {
		return null;
	}

	@Override
	public Observable<TransactionPrepared> createTransactionStructure(List<GeneratedOutput> outputs, List<Output> inputs, List<RandomOutput> randomOutputs, double mixin, double feeAmount, String paymentId, long unlockTime, Object extraData) {
		return null;
	}

	@Override
	public Observable<TransactionPrepared> prepareTransaction(List<GeneratedOutput> outputs, List<Output> inputs, List<RandomOutput> randomOutputs, double mixin, double feeAmount, String paymentId, long unlockTime, Object extraData, String randomKey) {
		return null;
	}

	@Override
	public Observable<Transaction> completeTransaction(TransactionPrepared preparedTransaction, String privateSpendKey) {
		return null;
	}
}
