package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerTransportRequiredException.java
 *
 * A Ledger transport is required.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerTransportRequiredException extends WalletException {
    public WalletLedgerTransportRequiredException() {
        super("A ledger device transport is required.");
    }
}
