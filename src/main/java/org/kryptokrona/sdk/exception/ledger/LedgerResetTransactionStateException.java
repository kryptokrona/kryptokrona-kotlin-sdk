package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerResetTransactionStateException.java
 *
 * Could not reset the transaction state. Please close and re-open the application
 * on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerResetTransactionStateException extends LedgerException {
	public LedgerResetTransactionStateException() {
		super(
				"Could not reset the transaction state. Please close and re-open the application " +
						"on the device."
		);
	}
}