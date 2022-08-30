package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerInputNotInSetException.java
 *
 * The calculated input (real output key) is not in the set of mixins supplied.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerInputNotInSetException extends LedgerException {
	public LedgerInputNotInSetException() {
		super("The calculated input (real output key) is not in the set of mixins supplied.");
	}
}
