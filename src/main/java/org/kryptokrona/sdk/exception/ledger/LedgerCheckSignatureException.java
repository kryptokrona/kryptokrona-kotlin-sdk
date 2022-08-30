package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerCheckSignatureException.java
 *
 * Error encountered when checking the signature using the supplied values.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerCheckSignatureException extends LedgerException {
	public LedgerCheckSignatureException() {
		super("Error encountered when checking the signature using the supplied values.");
	}
}
