package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionAmountException.java
 *
 * Could not process the amount value supplied.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionAmountException extends LedgerException {
	public LedgerTransactionAmountException() {
		super(
				"Could not process the amount value supplied."
		);
	}
}
