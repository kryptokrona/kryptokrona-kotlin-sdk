package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerCompleteRingSignatureException.java
 *
 * Error encountered completing the ring signatures for the supplied values.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerCompleteRingSignatureException extends LedgerException {
	public LedgerCompleteRingSignatureException() {
		super("Error encountered completing the ring signatures for the supplied values.");
	}
}