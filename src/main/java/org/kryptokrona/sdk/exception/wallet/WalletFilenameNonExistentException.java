package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFilenameNonExistentException.java
 * <p>
 * The wallet filename given does not exist or the program does not have
 * permission to view it.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFilenameNonExistentException extends WalletException {
	public WalletFilenameNonExistentException() {
		super(
				"The filename you are attempting to open does not exist, " +
						"or the wallet does not have permission to open it."
		);
	}
}
