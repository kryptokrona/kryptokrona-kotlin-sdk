package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerKeccakException.java
 *
 * Error encountered performing Keccak hashing method on device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerKeccakException extends LedgerException {
	public LedgerKeccakException() {
		super("Error encountered performing Keccak hashing method on device.");
	}
}
