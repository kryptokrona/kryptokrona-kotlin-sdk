package org.kryptokrona.sdk.validator;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.exception.wallet.*;
import org.kryptokrona.sdk.wallet.Address;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.model.util.FeeType;
import org.kryptokrona.sdk.wallet.WalletSub;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WalletValidator {

	/**
	 * Verifies that the address given is valid.
	 *
	 * @param address The address to validate.
	 * @param integratedAddressAllowed Should an integrated address be allowed?
	 * @return Returns true if the address is valid, otherwise throws exception descripting the error
	 */
	public Observable<Boolean> validateAddress(String address, boolean integratedAddressAllowed) {
		return Observable.empty();
	}

	/**
	 * Verifies that the addresses given are valid.
	 *
	 * @param addresses The addresses to validate
	 * @param integratedAddressesAllowed Should we allow integrated addresses?
	 * @return Returns an Observable of type boolean if address is valid, otherwise throws exception describing the error
	 */
	public Observable<Boolean> validateAddresses(List<String> addresses, boolean integratedAddressesAllowed)
			throws WalletException {

		var alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

		for (String address : addresses) {
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

		return Observable.just(true);
	}

	/**
	 * Validate the amounts being sent are valid, and the addresses are valid.
	 *
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public Observable<Boolean> validateDestionations(List<Map<String, Number>> destinations) {
		return Observable.empty();
	}

	/**
	 * Validate that the payment ID's included in integrated addresses are valid.
	 * You should have already called validateAddresses() before this function.
	 *
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public Observable<Boolean> validateIntegratedAddresses(List<Map<String, Number>> destinations, String paymentID) {
		return Observable.empty();
	}

	/**
	 * Validate the addresses given are both valid, and exist in the sub wallet.
	 *
	 * @param addresses List of addresses to validate
	 * @param subWallets List of sub wallets to use in validation
	 * @return Returns SUCCESS if valid, otherwise a WalletError describing the error
	 */
	public Observable<Boolean> validateOurAddresses(List<String> addresses, List<WalletSub> subWallets) {
		return Observable.empty();
	}

	/**
	 * Validate that the transfer amount + fee is valid, and we have enough balance
	 * Note: Does not verify amounts are positive / integer, validateDestinations
	 * handles that.
	 *
	 * @return Returns true if valid, otherwise an exception describing the error
	 */
	public Observable<Boolean> validateAmount(
			List<Map<String, Number>> destinations,
			FeeType feetype,
			List<String> subWalletsToTakeFrom,
			List<WalletSub> subWallets,
			long currentHeight) {
		return Observable.empty();
	}

	/**
	 * Validates mixin is valid and in allowed range
	 *
	 * @param mixin The mixin to validate
	 * @param height Height for getting the mixin
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public Observable<Boolean> validateMixin(long mixin, long height)
			throws WalletNegativeValueGivenException, WalletMixinTooSmallException, WalletMixinTooBigException {
		if (mixin < 0) {
			throw new WalletNegativeValueGivenException();
		}

		Map<String, Long> mixinLimitsByHeight = Config.MIXIN_LIMITS.getMixinLimitsByHeight(height);

		if (mixin < mixinLimitsByHeight.get("minMixin")) {
			throw new WalletMixinTooSmallException();
		}

		if (mixin > mixinLimitsByHeight.get("maxMixin")) {
			throw new WalletMixinTooBigException();
		}

		return Observable.just(true);
	}

	/**
	 * Validates the payment ID is valid (or an empty string)
	 *
	 * @param paymentID The payment ID to validate
	 * @param allowEmptyString If we want to allow an empty string
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public Observable<Boolean> validatePaymentID(String paymentID, boolean allowEmptyString)
			throws WalletPaymentIdWrongLengthException, WalletPaymentIdInvalidException {

		if (Objects.equals(paymentID, "") && allowEmptyString) {
			return Observable.just(true);
		}

		if (paymentID.length() != 64) {
			throw new WalletPaymentIdWrongLengthException();
		}

		if (!paymentID.matches("^([a-zA-Z0-9]{64})?$")) {
			throw new WalletPaymentIdInvalidException();
		}

		return Observable.just(true);
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
