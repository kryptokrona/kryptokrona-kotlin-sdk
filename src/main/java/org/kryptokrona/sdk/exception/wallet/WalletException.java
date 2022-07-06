package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletException.java
 *
 * Abstract Wallet Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public abstract class WalletException extends Exception {
    public WalletException(String errorMessage) {
        super(errorMessage);
    }
}
