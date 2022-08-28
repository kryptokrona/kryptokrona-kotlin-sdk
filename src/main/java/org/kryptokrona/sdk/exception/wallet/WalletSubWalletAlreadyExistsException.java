package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletSubWalletAlreadyExistsException.java
 * <p>
 * Attempted to add a subwallet which already exists in the container.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletSubWalletAlreadyExistsException extends WalletException {
	public WalletSubWalletAlreadyExistsException() {
		super("A subwallet with the given key already exists.");
	}
}
