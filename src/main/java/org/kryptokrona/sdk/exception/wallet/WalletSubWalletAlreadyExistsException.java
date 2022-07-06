package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletSubWalletAlreadyExistsException.java
 *
 * Attempted to add a subwallet which already exists in the container.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletSubWalletAlreadyExistsException extends WalletException {
    public WalletSubWalletAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
