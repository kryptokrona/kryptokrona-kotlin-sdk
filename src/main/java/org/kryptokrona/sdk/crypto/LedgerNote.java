package org.kryptokrona.sdk.crypto;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.address.Address;
import org.kryptokrona.sdk.address.AddressPrefix;
import org.kryptokrona.sdk.transaction.*;

import java.util.List;

public class LedgerNote implements CryptoNote {

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
	public Observable<String> createIntegratedAddress(String address, String paymentId, AddressPrefix prefix) {
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
