package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletHashWrongLengthException.java
 *
 * Hash not 64 chars.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletHashWrongLengthException extends WalletException {
    public WalletHashWrongLengthException(String errorMessage) {
        super(errorMessage);
    }
}
