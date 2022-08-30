package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionInitException.java
 *
 * Could not reset transaction state tracking information on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionInitException extends LedgerException {
	public LedgerTransactionInitException() {
		super(
				"Could not reset transaction state tracking information on the device."
		);
	}
}

