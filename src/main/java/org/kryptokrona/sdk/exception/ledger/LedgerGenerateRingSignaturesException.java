package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerGenerateRingSignaturesException.java
 *
 * Error encountered generating the ring signatures for the supplied values.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class
LedgerGenerateRingSignaturesException extends LedgerException {
	public LedgerGenerateRingSignaturesException() {
		super("Error encountered generating the ring signatures for the supplied values.");
	}
}
