package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletCantGetFakeOutputsException.java
 *
 * Can't get mixin/fake outputs from the daemon, and mixin is not zero.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletCantGetFakeOutputsException extends WalletException {
    public WalletCantGetFakeOutputsException(String errorMessage) {
        super(errorMessage);
    }
}
