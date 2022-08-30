package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionLoadOutputException.java
 *
 * Could not load the transaction output to the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionLoadOutputException extends LedgerException {
	public LedgerTransactionLoadOutputException() {
		super(
				"Could not load the transaction output to the device."
		);
	}
}