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

package org.mjovanc.kryptokrona.validator;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.exception.wallet.*;
import org.mjovanc.kryptokrona.model.util.FeeType;
import org.mjovanc.kryptokrona.wallet.Address;
import org.mjovanc.kryptokrona.wallet.SubWallets;
import org.mjovanc.kryptokrona.config.Config;
import org.mjovanc.kryptokrona.exception.wallet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * WalletValidator.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletValidator {

	/**
	 * Verifies that the address given is valid.
	 *
	 * @param address                  The address to validate.
	 * @param integratedAddressAllowed Should an integrated address be allowed?
	 * @return Returns true if the address is valid, otherwise throws exception descripting the error
	 */
	public static Observable<Boolean> validateAddress(String address, boolean integratedAddressAllowed) throws WalletException {
		return validateAddresses(List.of(address), integratedAddressAllowed);
	}

	/**
	 * Verifies that the addresses given are valid.
	 *
	 * @param addresses                  The addresses to validate
	 * @param integratedAddressesAllowed Should we allow integrated addresses?
	 * @return Returns an Observable of type boolean if address is valid, otherwise throws exception describing the error
	 */
	public static Observable<Boolean> validateAddresses(List<String> addresses, boolean integratedAddressesAllowed)
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
	public static Observable<Boolean> validateDestinations(Map<String, Double> destinations) throws WalletException {
		if (destinations.size() == 0) {
			throw new WalletNoDestinationGivenException();
		}

		List<String> destinationAddresses = new ArrayList<>();

		for (Map.Entry<String, Double> entry : destinations.entrySet()) {
			if (entry.getValue().intValue() == 0) {
				throw new WalletAmountIsZeroException();
			}

			if (entry.getValue().intValue() < 0) {
				throw new WalletNegativeValueGivenException();
			}

			destinationAddresses.add(entry.getKey());
		}

		return validateAddresses(destinationAddresses, true);
	}

	/**
	 * Validate that the payment ID's included in integrated addresses are valid.
	 * You should have already called validateAddresses() before this function.
	 *
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public static Observable<Boolean> validateIntegratedAddresses(Map<String, Double> destinations, final String paymentID)
		throws WalletAddressChecksumMismatchException {
		for (var destination : destinations.keySet()) {
			if (destination.length() != Config.INTEGRATED_ADDRESS_LENGTH) {
				continue;
			}

			// extract the payment id
			Address.fromAddress(destination, Config.ADDRESS_PREFIX)
				.subscribe(parsedAddress -> {
					if (!Objects.equals(paymentID, parsedAddress.getPaymentId())) {
						throw new WalletConflictingPaymentIdsException();
					}
				});
		}

		return Observable.just(true);
	}

	/**
	 * Validate the addresses given are both valid, and exist in the sub wallet.
	 *
	 * @param addresses  List of addresses to validate
	 * @param subWallets List of sub wallets to use in validation
	 * @return Returns SUCCESS if valid, otherwise a WalletError describing the error
	 */
	public static Observable<Boolean> validateOurAddresses(List<String> addresses, SubWallets subWallets) throws WalletException {
		validateAddresses(addresses, false)
			.subscribe(validity -> {
				for (var address : addresses) {
					Address.fromAddress(address, Config.ADDRESS_PREFIX)
						.subscribe(parsedAddress -> {
							var keys = subWallets.getPublicSpendKeys();

							//TODO: below is probably not finished yet
							if (!keys.contains(parsedAddress.getSpendKeys())) {
								throw new WalletAddressNotInWalletException();
							}
						});
				}
			});

		return Observable.just(true);
	}

	/**
	 * Validate that the transfer amount + fee is valid, and we have enough balance
	 * Note: Does not verify amounts are positive / integer, validateDestinations
	 * handles that.
	 *
	 * @return Returns true if valid, otherwise an exception describing the error
	 */
	public static Observable<Boolean> validateAmount(
		Map<String, Double> destinations,
		FeeType feeType,
		List<String> subWalletsToTakeFrom,
		SubWallets subWallets,
		long currentHeight
	) throws WalletFeeTooSmallException {

		if (!feeType.isFeePerByte() && !feeType.isFixedFee()) {
			throw new WalletFeeTooSmallException();
		}

		subWallets.getBalance(currentHeight, subWalletsToTakeFrom)
			.subscribe(summedBalance -> {
				// gets the sum of all map values of destinations
				var totalAmount = destinations.values().stream().mapToDouble(Double::doubleValue).sum();

				if (feeType.isFixedFee()) {
					totalAmount += feeType.getFixedFee();
				}

				for (Double amount : summedBalance.keySet()) {
					if (totalAmount > amount) {
						throw new WalletNotEnoughBalanceException();
					}
				}

				if (totalAmount >= 2 * Math.pow(2, 64)) {
					throw new WalletWillOverflowException();
				}
			});

		return Observable.just(true);
	}

	/**
	 * Validates mixin is valid and in allowed range
	 *
	 * @param mixin  The mixin to validate
	 * @param height Height for getting the mixin
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public static Observable<Boolean> validateMixin(long mixin, long height)
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
	 * @param paymentID        The payment ID to validate
	 * @param allowEmptyString If we want to allow an empty string
	 * @return Returns true if valid, otherwise throws an exception describing the error
	 */
	public static Observable<Boolean> validatePaymentID(String paymentID, boolean allowEmptyString)
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

	/**
	 * Check if character exists in char array
	 *
	 * @param c     The character to check if it contains in char array
	 * @param array The char array to check against
	 * @return Returns true if it contains, otherwise false
	 */
	private static boolean contains(char c, char[] array) {
		for (char x : array) {
			if (x == c) {
				return true;
			}
		}
		return false;
	}
}
