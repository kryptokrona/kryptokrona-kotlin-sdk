package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidKeyFormatException.java
 *
 * Key is not 64 char hex.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidKeyFormatException extends WalletException {
    public WalletInvalidKeyFormatException(String errorMessage) {
        super(errorMessage);
    }
}
