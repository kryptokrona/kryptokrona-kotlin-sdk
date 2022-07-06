package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMnemonicInvalidWordException.java
 *
 * Mnemonic has a word that is not in the english word list.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMnemonicInvalidWordException extends WalletException {
    public WalletMnemonicInvalidWordException(String errorMessage) {
        super(errorMessage);
    }
}
