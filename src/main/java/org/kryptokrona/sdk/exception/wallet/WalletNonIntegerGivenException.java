package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNonIntegerGivenException.java
 *
 * Number is a float, not an integer.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNonIntegerGivenException extends WalletException {
    public WalletNonIntegerGivenException(String errorMessage) {
        super(errorMessage);
    }
}
