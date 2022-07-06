package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerTransportRequiredException.java
 *
 * A Ledger transport is required.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerTransportRequiredException extends WalletException {
    public WalletLedgerTransportRequiredException(String errorMessage) {
        super(errorMessage);
    }
}
