package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNegativeValueGivenException.java
 *
 * Value given is negative, but must be >= 0.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNegativeValueGivenException extends WalletException {
    public WalletNegativeValueGivenException(String errorMessage) {
        super(errorMessage);
    }
}
