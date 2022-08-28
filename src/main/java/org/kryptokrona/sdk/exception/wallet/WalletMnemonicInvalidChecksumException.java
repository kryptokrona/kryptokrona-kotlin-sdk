package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMnemonicInvalidChecksumException.java
 * <p>
 * The mnemonic seed has an invalid checksum word.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMnemonicInvalidChecksumException extends WalletException {
	public WalletMnemonicInvalidChecksumException() {
		super("The mnemonic seed given has an invalid checksum word.");
	}
}
