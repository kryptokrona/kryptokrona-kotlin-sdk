package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerNotPublicKeyException.java
 *
 * The value supplied is not a valid public key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerNotPublicKeyException extends LedgerException {
	public LedgerNotPublicKeyException() {
		super("The value supplied is not a valid public key.");
	}
}