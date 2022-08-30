package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerResetWalletKeysException.java
 *
 * Could not reset the wallet keys on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerResetWalletKeysException extends LedgerException {
	public LedgerResetWalletKeysException() {
		super(
				"Could not reset the wallet keys on the device."
		);
	}
}