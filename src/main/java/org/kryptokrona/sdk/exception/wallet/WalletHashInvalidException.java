package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletHashInvalidException.java
 * <p>
 * Hash not hex.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletHashInvalidException extends WalletException {
	public WalletHashInvalidException() {
		super("The hash given is not a hex string (A-Za-z0-9)");
	}
}
