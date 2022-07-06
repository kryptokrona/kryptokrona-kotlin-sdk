package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletTransactionUnexpectedFeeException.java
 *
 * Transaction fee is not the same as specified fee.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletTransactionUnexpectedFeeException extends WalletException {
    public WalletTransactionUnexpectedFeeException(String errorMessage) {
        super(errorMessage);
    }
}
