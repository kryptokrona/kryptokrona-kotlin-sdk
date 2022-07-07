package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletPreparedTransactionNotFoundException.java
 *
 * The prepared transaction hash specified does not exist, either because
 * it never existed, or because the wallet was restarted and the prepared
 * transaction state was lost.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletPreparedTransactionNotFoundException extends WalletException {
    public WalletPreparedTransactionNotFoundException() {
        super(
                "The prepared transaction hash given does not exist, either " +
                "because it never existed or because the wallet process was " +
                "restarted and the previously prepared transactions were lost. " +
                "Please re-prepare and re-send the transaction, ensuring you " +
                "specify the correct transaction hash."
        );
    }
}
