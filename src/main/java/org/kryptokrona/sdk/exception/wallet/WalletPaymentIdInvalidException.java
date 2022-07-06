package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletPaymentIdInvalidException.java
 *
 * The payment ID is not hex.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletPaymentIdInvalidException extends WalletException {
    public WalletPaymentIdInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
