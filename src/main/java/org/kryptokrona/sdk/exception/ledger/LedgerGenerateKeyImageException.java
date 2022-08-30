package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerGenerateKeyImageException.java
 *
 * Error encountered generating a key image using the supplied values.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerGenerateKeyImageException extends LedgerException {
	public LedgerGenerateKeyImageException() {
		super("Error encountered generating a key image using the supplied values.");
	}
}
