package org.kryptokrona.sdk.crypto;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.wallet.Address;
import org.kryptokrona.sdk.transaction.*;

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

	Observable<String> createIntegratedAddress(String address, String paymentId, AddressPrefix prefix);

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
