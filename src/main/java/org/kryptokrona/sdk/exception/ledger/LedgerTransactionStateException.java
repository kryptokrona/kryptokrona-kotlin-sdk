package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionStateException.java
 *
 * The device is currently in a transaction construction state and this operation is
 * prohibited at this time.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionStateException extends LedgerException {
	public LedgerTransactionStateException() {
		super(
				"The device is currently in a transaction construction state and this operation is " +
						"prohibited at this time."
		);
	}
}