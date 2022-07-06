package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletTransactionPrivateKeyNotFoundException.java
 *
 * Couldn't find the private key for this hash.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletTransactionPrivateKeyNotFoundException extends WalletException {
    public WalletTransactionPrivateKeyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
