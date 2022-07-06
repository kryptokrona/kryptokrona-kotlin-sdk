package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletWillOverflowException.java
 *
 * Operation will cause int overflow.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletWillOverflowException extends WalletException {
    public WalletWillOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
