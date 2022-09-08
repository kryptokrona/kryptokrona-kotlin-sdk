package org.kryptokrona.sdk.validator;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.address.Address;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.exception.wallet.WalletAddressIsIntegratedException;
import org.kryptokrona.sdk.exception.wallet.WalletAddressNotBase58Exception;
import org.kryptokrona.sdk.exception.wallet.WalletAddressWrongLengthException;
import org.kryptokrona.sdk.exception.wallet.WalletException;
import org.kryptokrona.sdk.model.util.FeeType;
import org.kryptokrona.sdk.wallet.WalletSub;

import java.util.List;
import java.util.Map;

public class WalletValidator {

	public Observable<Void> validateAddress(String address, boolean integratedAddressesAllowed) {
		return Observable.empty();
	}

	public Observable<Boolean> validateAddresses(List<String> addresses, boolean integratedAddressesAllowed)
			throws WalletException {

		var alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

		for (String address : addresses)  {
			// verify that address lengths are correct
			if (
					address.length() != Config.STANDARD_ADDRESS_LENGTH &&
					address.length() != Config.INTEGRATED_ADDRESS_LENGTH) {
				throw new WalletAddressWrongLengthException();
			}

			// verify every address character is in the base58 set
			char[] chars = address.toCharArray();
			for (char c : chars) {
				if (!contains(c, alphabet.toCharArray())) {
					throw new WalletAddressNotBase58Exception();
				}
			}

			// verify it's not an integrated, if those aren't allowed
			Address.fromAddress(address, Config.ADDRESS_PREFIX).subscribe(a -> {
				if (a.getPaymentId().length() != 0 && !integratedAddressesAllowed) {
					throw new WalletAddressIsIntegratedException();
				}
			});
		}

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

	private boolean contains(char c, char[] array) {
		for (char x : array) {
			if (x == c) {
				return true;
			}
		}
		return false;
	}


	private void assertList(Object correctType, List list) {

	}

	private void assertType() {
		// assert the type here
	}



}
