package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerDerivationToScalarException.java
 *
 * Error encountered when calculating the scalar of the derivation.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerDerivationToScalarException extends LedgerException {
	public LedgerDerivationToScalarException() {
		super("Error encountered when calculating the scalar of the derivation.");
	}
}