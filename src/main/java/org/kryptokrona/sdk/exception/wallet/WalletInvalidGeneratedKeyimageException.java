package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidGeneratedKeyimageException.java
 * <p>
 * The key image generated was not valid. This is most likely a programmer error.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidGeneratedKeyimageException extends WalletException {
	public WalletInvalidGeneratedKeyimageException() {
		super(
				"The key image we generated is invalid - probably a " +
						"programmer error, or a corrupted wallet."
		);
	}
}
