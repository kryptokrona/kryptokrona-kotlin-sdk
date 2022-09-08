package org.kryptokrona.sdk.address;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.Setter;
import org.kryptokrona.sdk.crypto.Cache;
import org.kryptokrona.sdk.crypto.KeyPair;

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

	private List<KeyPair> keys;

	private String language;

	private long subwalletIndex;

	private AddressPrefix prefix;

	private Cache cached;

	public Address() {
		// this.keys =
		this.language = "english";
		this.subwalletIndex = 0;
		this.prefix = new AddressPrefix();
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

	public static Observable<Address> fromAddress(String address, long prefix) {

		/* const decodedAddress = Base58.decode(address);

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

		if (expectedChecksum !== checksum) {
			throw new Error('Could not parse address: checksum mismatch');
		}

        const result = new Address();

		result.m_paymentId = paymentId;

		result.m_keys = await ED25519.Keys.from(
				await ED25519.KeyPair.from(publicSpend),
				await ED25519.KeyPair.from(publicView)
		);

		return result;*/

		return null;
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
