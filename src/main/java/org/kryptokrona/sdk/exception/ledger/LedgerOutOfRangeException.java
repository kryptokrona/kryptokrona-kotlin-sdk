package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerOutOfRangeException.java
 *
 * The value supplied exceeds the permitted range of such values.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerOutOfRangeException extends LedgerException {
	public LedgerOutOfRangeException() {
		super("The value supplied exceeds the permitted range of such values.");
	}
}
