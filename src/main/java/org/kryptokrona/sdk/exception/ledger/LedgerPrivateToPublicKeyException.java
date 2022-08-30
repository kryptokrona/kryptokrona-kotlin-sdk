package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerPrivateToPublicKeyException.java
 *
 * Error encountered calculating the public key for the supplied private key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerPrivateToPublicKeyException extends LedgerException {
	public LedgerPrivateToPublicKeyException() {
		super("Error encountered calculating the public key for the supplied private key.");
	}
}