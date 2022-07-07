package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletWillOverflowException.java
 *
 * Operation will cause int overflow.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletWillOverflowException extends WalletException {
    public WalletWillOverflowException() {
        super(
                "This operation will cause integer overflow. Please decrease " +
                "the amounts you are sending."
        );
    }
}
