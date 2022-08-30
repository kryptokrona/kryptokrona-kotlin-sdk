package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerOperationFailedToCompleteException.java
 *
 * Operation failed to complete.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerOperationFailedToCompleteException extends LedgerException {
	public LedgerOperationFailedToCompleteException() {
		super("Operation failed to complete.");
	}
}
