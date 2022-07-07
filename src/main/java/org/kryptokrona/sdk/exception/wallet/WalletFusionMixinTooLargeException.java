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
    public WalletFusionMixinTooLargeException() {
        super(
                "The mixin value given is too high to be accepted by the " +
                "network (based on the current height known by the wallet)"
        );
    }
}
