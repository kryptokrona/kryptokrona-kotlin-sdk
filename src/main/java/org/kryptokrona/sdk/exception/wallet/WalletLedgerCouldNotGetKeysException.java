package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerCouldNotGetKeysException.java
 *
 * Could not retrieve wallet keys from Ledger device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerCouldNotGetKeysException extends WalletException {
    public WalletLedgerCouldNotGetKeysException(String errorMessage) {
        super(errorMessage);
    }
}
