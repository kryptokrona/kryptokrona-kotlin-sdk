package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMixinTooSmallException.java
 *
 * The mixin given is too low for the current height known by the wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMixinTooSmallException extends WalletException {
    public WalletMixinTooSmallException(String errorMessage) {
        super(errorMessage);
    }
}
