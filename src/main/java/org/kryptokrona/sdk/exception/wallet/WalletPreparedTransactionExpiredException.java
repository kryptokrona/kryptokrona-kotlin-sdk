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
    public WalletPreparedTransactionExpiredException() {
        super(
                "The prepared transaction contains inputs that have since " +
                "been spent or are no longer available, probably due to sending " +
                "another transaction in between preparing this transaction and " +
                "sending it. The prepared transaction has been cancelled."
        );
    }
}
