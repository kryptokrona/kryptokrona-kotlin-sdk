package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMnemonicInvalidWordException.java
 * <p>
 * Mnemonic has a word that is not in the english word list.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMnemonicInvalidWordException extends WalletException {
	public WalletMnemonicInvalidWordException() {
		super(
				"The mnemonic seed given has a word that is not present in " +
						"the english word list."
		);
	}
}
