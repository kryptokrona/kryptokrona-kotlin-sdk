package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerVarintDataRangeException.java
 *
 * The data provided could not be encoded or decoded given the maximum buffer length.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerVarintDataRangeException extends LedgerException {
	public LedgerVarintDataRangeException() {
		super("The data provided could not be encoded or decoded given the maximum buffer length.");
	}
}
