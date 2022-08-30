package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerDerivePublicKeyException.java
 *
 * Could not derive public key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerDerivePublicKeyException extends LedgerException {
	public LedgerDerivePublicKeyException() {
		super("Could not derive public key.");
	}
}