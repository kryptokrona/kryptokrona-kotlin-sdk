package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerUnknownErrorException.java
 *
 * An unknown error occurred.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerUnknownErrorException extends LedgerException {
	public LedgerUnknownErrorException() {
		super("An unknown error occurred.");
	}
}
