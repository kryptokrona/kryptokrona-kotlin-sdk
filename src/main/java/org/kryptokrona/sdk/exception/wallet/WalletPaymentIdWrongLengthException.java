package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletPaymentIdWrongLengthException.java
 *
 * Payment ID is not 64 chars.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletPaymentIdWrongLengthException extends WalletException {
    public WalletPaymentIdWrongLengthException(String errorMessage) {
        super(errorMessage);
    }
}
