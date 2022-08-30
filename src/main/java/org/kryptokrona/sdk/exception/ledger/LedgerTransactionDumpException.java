package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionDumpException.java
 *
 * Could not export the completed transaction from the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionDumpException extends LedgerException {
	public LedgerTransactionDumpException() {
		super(
				"Could not export the completed transaction from the device."
		);
	}
}