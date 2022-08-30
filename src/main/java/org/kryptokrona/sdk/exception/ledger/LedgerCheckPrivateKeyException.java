package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerCheckPrivateKeyException.java
 *
 * Error encountered when checking private key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerCheckPrivateKeyException extends LedgerException {
	public LedgerCheckPrivateKeyException() {
		super("Error encountered when checking private key.");
	}
}
