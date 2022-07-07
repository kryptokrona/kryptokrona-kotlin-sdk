package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMixinTooBigException.java
 *
 * The mixin given is too large for the current height known by the wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMixinTooBigException extends WalletException {
    public WalletMixinTooBigException() {
        super(
                "The mixin value given is too high to be accepted by the " +
                "network (based on the current height known by the wallet)"
        );
    }
}
