package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidPublicKeyException.java
 *
 * Not on ed25519 curve.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidPublicKeyException extends WalletException {
    public WalletInvalidPublicKeyException(String errorMessage) {
        super(errorMessage);
    }
}
