package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerGenerateSignatureException.java
 *
 * 'Error encountered generating the signature for the supplied values.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerGenerateSignatureException extends LedgerException {
	public LedgerGenerateSignatureException() {
		super("'Error encountered generating the signature for the supplied values.");
	}
}