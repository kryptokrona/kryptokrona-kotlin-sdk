package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerCheckRingSignatureException.java
 *
 * Error encountered when checking the ring signatures supplied.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerCheckRingSignatureException extends LedgerException {
	public LedgerCheckRingSignatureException() {
		super("Error encountered when checking the ring signatures supplied.");
	}
}
