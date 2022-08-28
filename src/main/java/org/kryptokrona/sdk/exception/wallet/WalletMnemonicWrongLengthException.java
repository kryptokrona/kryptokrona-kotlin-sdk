package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMnemonicWrongLengthException.java
 * <p>
 * Mnemonic seed is not 25 words.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMnemonicWrongLengthException extends WalletException {
	public WalletMnemonicWrongLengthException() {
		super("The mnemonic seed given is the wrong length.");
	}
}
