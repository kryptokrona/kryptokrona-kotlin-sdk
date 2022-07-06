package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIncorrectFilePrefixException.java
 *
 * The wallet does not have the wallet identifier prefix.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIncorrectFilePrefixException extends WalletException {
    public WalletIncorrectFilePrefixException(String errorMessage) {
        super(errorMessage);
    }
}
