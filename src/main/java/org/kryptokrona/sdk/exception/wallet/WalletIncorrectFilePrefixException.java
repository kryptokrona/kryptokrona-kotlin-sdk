package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIncorrectFilePrefixException.java
 * <p>
 * The wallet does not have the wallet identifier prefix.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIncorrectFilePrefixException extends WalletException {
	public WalletIncorrectFilePrefixException() {
		super(
				"This file is not a wallet file, or is not a wallet file " +
						"type supported by this wallet version."
		);
	}
}
