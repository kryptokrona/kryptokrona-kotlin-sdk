package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerCheckPublicKeyException.java
 *
 * Error encountered when checking public key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerCheckPublicKeyException extends LedgerException {
	public LedgerCheckPublicKeyException() {
		super("Error encountered when checking public key.");
	}
}