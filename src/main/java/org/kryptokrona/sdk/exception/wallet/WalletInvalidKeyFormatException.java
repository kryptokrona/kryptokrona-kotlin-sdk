package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidKeyFormatException.java
 * <p>
 * Key is not 64 char hex.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidKeyFormatException extends WalletException {
	public WalletInvalidKeyFormatException() {
		super(
				"The public/private key or hash given is not a 64 char " +
						"hex string."
		);
	}
}
