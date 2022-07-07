package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNegativeValueGivenException.java
 *
 * Value given is negative, but must be >= 0.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNegativeValueGivenException extends WalletException {
    public WalletNegativeValueGivenException() {
        super(
                "The input for this operation must be greater than or " +
                "equal to zero, but a negative number was given."
        );
    }
}
