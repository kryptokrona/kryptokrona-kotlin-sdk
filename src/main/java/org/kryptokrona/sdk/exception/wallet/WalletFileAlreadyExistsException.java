package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFileAlreadyExistsException.java
 * <p>
 * Trying to create a wallet file which already exists.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFileAlreadyExistsException extends WalletException {
	public WalletFileAlreadyExistsException() {
		super(
				"The wallet file you are attempting to create already " +
						"exists. Please delete it first."
		);
	}
}
