package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletPaymentIdInvalidException.java
 *
 * The payment ID is not hex.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletPaymentIdInvalidException extends WalletException {
    public WalletPaymentIdInvalidException() {
        super("The payment ID given is not a hex string (A-Za-z0-9)");
    }
}
