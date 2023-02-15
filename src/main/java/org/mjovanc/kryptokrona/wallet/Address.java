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

package org.mjovanc.kryptokrona.wallet;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.Setter;
import org.mjovanc.kryptokrona.crypto.Base58;
import org.mjovanc.kryptokrona.crypto.Cache;
import org.mjovanc.kryptokrona.crypto.KeyPair;
import org.mjovanc.kryptokrona.exception.wallet.WalletAddressChecksumMismatchException;
import org.mjovanc.kryptokrona.config.Config;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Address.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class Address {

	private String paymentId;

	private String seed;

	private KeyPair keyPair;

	private String language;

	private long subwalletIndex;

	private long prefix;

	private Cache cached;

	public Address() {
		// this.keys = new Keys();
		this.language = "english";
		this.cached = new Cache();
	}

	public String getMnemonicSeed() {
		return seed.length() > 0 ? encodeSeed(this.seed) : "";
	}

	public String getPaymentId() {
		return "";
	}

	public KeyPair getSpendKeys() {
		return null;
	}

	public void setSpendKeys(KeyPair spendKeys) {

	}

	public KeyPair getViewKeys() {
		return null;
	}

	public void setViewKeys(KeyPair viewKeys) {

	}

	private String encodeSeed(String seed) {
		return "";
	}

	/**
	 * Creates a new address object from a Base58 address
	 *
	 * @param address       The public address to decode
	 * @param addressPrefix The address prefix
	 * @return Address object
	 */
	public static Observable<Address> fromAddress(String address, long addressPrefix) throws WalletAddressChecksumMismatchException {
		var decodedAddress = Base58.decode(address);

		var reader = new InputStreamReader(new ByteArrayInputStream(decodedAddress));

		//TODO: need to implement the rest here
		var decodedPrefix = reader.toString();
		System.out.println(decodedPrefix);

		var paymentId = "";

		var publicSpend = reader.hashCode();
		var publicView = reader.hashCode();
		var expectedChecksum = "change this later";

		var checksum = "change this later";

		if (!expectedChecksum.equals(checksum)) {
			throw new WalletAddressChecksumMismatchException();
		}

		var result = new Address();
		result.setPaymentId(paymentId);

		// result.setKeys();

		/*
        const reader = new Reader(decodedAddress);

        const decodedPrefix = reader.bytes(prefix.size).toString('hex');

		if (decodedPrefix !== prefix.hex) {
			throw new Error('Invalid address prefix');
		}

		let paymentId = '';

		if (reader.unreadBytes > ((SIZES.KEY * 2) + SIZES.CHECKSUM)) {
			paymentId = reader.hex(SIZES.KEY * 2);
		}

        const publicSpend = reader.hash();
        const publicView = reader.hash();
        const expectedChecksum = reader.bytes(SIZES.CHECKSUM).toString('hex');

        const checksum = (new Reader(
				await TurtleCoinCrypto.cn_fast_hash(decodedPrefix + paymentId + publicSpend + publicView)
		)).bytes(SIZES.CHECKSUM).toString('hex');

		result.m_keys = await ED25519.Keys.from(
				await ED25519.KeyPair.from(publicSpend),
				await ED25519.KeyPair.from(publicView)
		);
			*/

		return Observable.just(result);
	}

	/**
	 * Creates a new address object using the supplied public keys.
	 *
	 * @param publicSpendKey the public spend key
	 * @param publicViewKey  the public view key
	 * @param paymentId      the payment ID
	 * @return A new address Object
	 */
	public static Observable<Address> fromPublicKeys(String publicSpendKey, String publicViewKey, String paymentId) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		return Observable.empty();
	}

	public static Observable<Address> fromViewOnlyKeys(String publicSpendKey, String publicViewKey, String paymentId) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		return Observable.empty();
	}

	public static Observable<Address> fromKeys(KeyPair spendKeys) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		return Observable.empty();
	}

	public static Observable<Address> fromMnemonic(List<String> mnemonic, String language) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		return Observable.empty();
	}

	public static Observable<Address> fromSeed(String seed, String language) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		return Observable.just(new Address());
	}

	/**
	 * Creates a new address object from entropy (new address)
	 *
	 * @param entropy data to use for entropy to feed to the underlying random generation function
	 * @param language the language of the mnemonic phrase
	 *
	 * @return Observable - a new address object
	 */
	public static Observable<Address> fromEntropy(String entropy, String language) {
		var seed = Address.generateSeed(entropy, language).blockingSingle();
		var addressFromSeed = Address.fromSeed(seed, language).blockingSingle();

		return Observable.just(addressFromSeed);
	}

	/**
	 * Creates a new address object from entropy (new address)
	 *
	 * @param entropy data to use for entropy to feed to the underlying random generation function
	 * @param language the language of the mnemonic phrase
	 * @return a new address object
	 */
	public static Observable<String> generateSeed(String entropy, String language) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		// var random = await ED25519.KeyPair.from etc.
		return Observable.just("test");
	}

	public static Observable<String> generateSubWallet(String privateSpendKey, long subwalletIndex, String language) {
		var addressPrefix = Config.ADDRESS_PREFIX; // remove this later and just use ADDRESS_PREFIX
		return Observable.empty();
	}

	private enum Sizes {
		KEY(32),
		CHECKSUM(4);

		private int code;

		Sizes(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
}
