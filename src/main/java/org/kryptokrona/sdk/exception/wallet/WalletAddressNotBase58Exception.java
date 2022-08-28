package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressNotBase58Exception.java
 * <p>
 * The address is not fully comprised of base58 characters.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressNotBase58Exception extends WalletException {
	public WalletAddressNotBase58Exception() {
		super(
				"The address contains invalid characters, that are not in " +
						"the base58 set."
		);
	}
}
