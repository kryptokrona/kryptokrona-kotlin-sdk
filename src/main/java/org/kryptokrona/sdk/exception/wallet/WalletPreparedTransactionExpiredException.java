package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletPreparedTransactionExpiredException.java
 *
 * The inputs that were included in a prepared transaction have since been
 * spent or are for some other reason no longer available.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletPreparedTransactionExpiredException extends WalletException {
    public WalletPreparedTransactionExpiredException(String errorMessage) {
        super(errorMessage);
    }
}
