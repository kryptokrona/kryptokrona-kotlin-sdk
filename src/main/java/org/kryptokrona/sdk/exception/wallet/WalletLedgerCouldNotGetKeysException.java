package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerCouldNotGetKeysException.java
 * <p>
 * Could not retrieve wallet keys from Ledger device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerCouldNotGetKeysException extends WalletException {
	public WalletLedgerCouldNotGetKeysException() {
		super("Could not retrieve wallet keys from Ledger device.");
	}
}
