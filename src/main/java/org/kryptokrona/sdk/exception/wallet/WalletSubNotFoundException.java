package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletSubNotFoundException.java
 *
 * Subwallet not found.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletSubNotFoundException extends WalletException {
	public WalletSubNotFoundException() {
		super("Subwallet not found.");
	}
}