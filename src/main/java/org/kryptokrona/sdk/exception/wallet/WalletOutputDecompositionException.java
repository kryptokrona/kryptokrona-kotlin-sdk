package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletOutputDecompositionException.java
 *
 * The transaction has more outputs than are permitted for the number
 * inputs that have been provided
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletOutputDecompositionException extends WalletException {
    public WalletOutputDecompositionException(String errorMessage) {
        super(errorMessage);
    }
}
