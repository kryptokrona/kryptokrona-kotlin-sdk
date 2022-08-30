package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerDerivePrivateKeyException.java
 *
 * TCould not derive the private key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerDerivePrivateKeyException extends LedgerException {
	public LedgerDerivePrivateKeyException() {
		super("Could not derive the private key.");
	}
}
