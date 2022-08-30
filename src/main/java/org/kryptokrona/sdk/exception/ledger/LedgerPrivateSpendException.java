package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerPrivateSpendException.java
 *
 * Could not access the private spend key on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerPrivateSpendException extends LedgerException {
	public LedgerPrivateSpendException() {
		super(
				"Could not access the private spend key on the device."
		);
	}
}