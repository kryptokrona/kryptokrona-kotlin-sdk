package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMixinTooBigException.java
 *
 * The mixin given is too large for the current height known by the wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMixinTooBigException extends WalletException {
    public WalletMixinTooBigException(String errorMessage) {
        super(errorMessage);
    }
}
