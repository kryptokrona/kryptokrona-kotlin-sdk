package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressChecksumMismatchException.java
 *
 * Checksum mismatch when parsing wallet address.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressChecksumMismatchException extends WalletException {
	public WalletAddressChecksumMismatchException() {
		super("Could not parse address: checksum mismatch.");
	}
}
