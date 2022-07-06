package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFusionMixinTooLargeException.java
 *
 * Mixin given for this fusion transaction is too large to be able to hit
 * the min input requirement.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFusionMixinTooLargeException extends WalletException {
    public WalletFusionMixinTooLargeException(String errorMessage) {
        super(errorMessage);
    }
}
