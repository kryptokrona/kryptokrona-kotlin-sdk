package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFileAlreadyExistsException.java
 *
 * Trying to create a wallet file which already exists.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFileAlreadyExistsException extends WalletException {
    public WalletFileAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
