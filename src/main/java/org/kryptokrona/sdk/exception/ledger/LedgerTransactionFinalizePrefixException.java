package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionInputOutputOutOfRangeException.java
 *
 * Could not finalize the transaction prefix on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionFinalizePrefixException extends LedgerException {
	public LedgerTransactionFinalizePrefixException() {
		super(
				"Could not finalize the transaction prefix on the device."
		);
	}
}
