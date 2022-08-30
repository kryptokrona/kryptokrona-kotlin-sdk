package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerGenerateKeyDerivationException.java
 *
 * Could not generate key derivation.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerGenerateKeyDerivationException extends LedgerException {
	public LedgerGenerateKeyDerivationException() {
		super("Could not generate key derivation.");
	}
}
