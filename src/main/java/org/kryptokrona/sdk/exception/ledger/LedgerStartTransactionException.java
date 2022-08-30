package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerStartTransactionException.java
 *
 * Could not initiate a transaction construction process on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerStartTransactionException extends LedgerException {
	public LedgerStartTransactionException() {
		super(
				"Could not initiate a transaction construction process on the device."
		);
	}
}
