package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidMnemonicException.java
 * <p>
 * The mnemonic seed is invalid for some reason, for example, it has the
 * wrong length, or an invalid checksum.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidMnemonicException extends WalletException {
	public WalletInvalidMnemonicException() {
		super("The mnemonic seed given is invalid.");
	}
}
