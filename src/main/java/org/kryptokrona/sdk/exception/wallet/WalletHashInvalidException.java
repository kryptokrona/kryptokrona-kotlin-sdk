package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletHashInvalidException.java
 *
 * Hash not hex.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletHashInvalidException extends WalletException {
    public WalletHashInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
