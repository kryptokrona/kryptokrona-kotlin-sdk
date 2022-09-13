package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletSubWalletAlreadyExistsException.java
 * <p>
 * Attempted to add a subwallet which already exists in the container.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletSubWalletNoPrimaryAddressException extends WalletException {
	public WalletSubWalletNoPrimaryAddressException() {
		super("Wallet has no primary address.");
	}
}

