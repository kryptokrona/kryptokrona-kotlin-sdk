package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerPublicKeyMismatchException.java
 *
 * The public key supplied does not match the calculated public key.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerPublicKeyMismatchException extends LedgerException {
	public LedgerPublicKeyMismatchException() {
		super("The public key supplied does not match the calculated public key.");
	}
}
