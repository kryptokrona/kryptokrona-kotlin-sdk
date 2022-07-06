package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIntegratedAddressPaymentIdInvalidException.java
 *
 * The payment ID encoded in the integrated address is not valid.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIntegratedAddressPaymentIdInvalidException extends WalletException {
    public WalletIntegratedAddressPaymentIdInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
