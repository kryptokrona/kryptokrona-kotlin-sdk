package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidPrivateKeyException.java
 *
 * Not on ed25519 curve.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidPrivateKeyException extends WalletException {
    public WalletInvalidPrivateKeyException(String errorMessage) {
        super(errorMessage);
    }
}
