package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletHashWrongLengthException.java
 * <p>
 * Hash not 64 chars.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletHashWrongLengthException extends WalletException {
	public WalletHashWrongLengthException() {
		super("The hash given is not 64 characters long.");
	}
}
