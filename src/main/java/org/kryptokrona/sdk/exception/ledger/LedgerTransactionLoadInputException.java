package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionLoadInputException.java
 *
 * Could not load the transaction input to the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionLoadInputException extends LedgerException {
	public LedgerTransactionLoadInputException() {
		super(
				"Could not load the transaction input to the device."
		);
	}
}