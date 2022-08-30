package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerException.java
 *
 * Abstract Daemon Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public abstract class LedgerException extends Exception {
	public LedgerException(String errorMessage) {
		super(errorMessage);
	}
}
