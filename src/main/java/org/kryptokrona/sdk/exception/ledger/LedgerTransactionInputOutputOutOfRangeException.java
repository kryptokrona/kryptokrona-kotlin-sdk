package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionInputOutputOutOfRangeException.java
 *
 * The output index of the input supplied exceeds the allowable device range.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionInputOutputOutOfRangeException extends LedgerException {
	public LedgerTransactionInputOutputOutOfRangeException() {
		super(
				"The output index of the input supplied exceeds the allowable device range."
		);
	}
}