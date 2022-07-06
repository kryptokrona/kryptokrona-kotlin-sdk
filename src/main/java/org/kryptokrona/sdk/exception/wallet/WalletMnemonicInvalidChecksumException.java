package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMnemonicInvalidChecksumException.java
 *
 * The mnemonic seed has an invalid checksum word.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMnemonicInvalidChecksumException extends WalletException {
    public WalletMnemonicInvalidChecksumException(String errorMessage) {
        super(errorMessage);
    }
}
