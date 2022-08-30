package org.kryptokrona.sdk.validator;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.model.util.FeeType;
import org.kryptokrona.sdk.wallet.WalletSub;

import java.util.List;
import java.util.Map;

public class WalletValidator {

	public Observable<Void> validateAddress(String address, boolean integratedAddressesAllowed) {
		return Observable.empty();
	}

	public Observable<Boolean> validateAddresses(List<String> addresses, boolean integratedAddressesAllowed) {
		return Observable.empty();
	}

	public Observable<Void> validateDestionations(List<Map<String, Number>> destinations) {
		return Observable.empty();
	}

	public Observable<Void> validateIntegratedAddresses(List<Map<String, Number>> destinations, String paymentID) {
		return Observable.empty();
	}

	public Observable<Void> validateOurAddresses(List<String> addresses, List<WalletSub> subWallets) {
		return Observable.empty();
	}

	public Observable<Void> validateAmount(
			List<Map<String, Number>> destinations,
			FeeType feetype,
			List<String> subWalletsToTakeFrom,
			List<WalletSub> subWallets,
			long currentHeight) {
		return Observable.empty();
	}

	public Observable<Void> validateMixin(long mixin, long height) {
		return Observable.empty();
	}

	public Observable<Void> validatePaymentID(String paymentID, boolean allowEmptyString) {
		return Observable.empty();
	}

}
