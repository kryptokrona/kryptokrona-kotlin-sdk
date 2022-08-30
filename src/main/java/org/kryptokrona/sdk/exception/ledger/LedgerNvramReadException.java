package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerNvramReadException.java
 *
 * There was an error reading information from device NVRAM.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerNvramReadException extends LedgerException {
	public LedgerNvramReadException() {
		super("There was an error reading information from device NVRAM.");
	}
}