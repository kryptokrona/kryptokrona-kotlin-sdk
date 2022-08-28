package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNonIntegerGivenException.java
 * <p>
 * Number is a float, not an integer.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNonIntegerGivenException extends WalletException {
	public WalletNonIntegerGivenException() {
		super("The number given is a float, not an integer.");
	}
}
