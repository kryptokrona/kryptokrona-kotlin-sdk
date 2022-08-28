package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNoDestionationGivenException.java
 * <p>
 * The destinations array is empty.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNoDestinationGivenException extends WalletException {
	public WalletNoDestinationGivenException() {
		super("The destinations array (amounts/addresses) is empty.");
	}
}
