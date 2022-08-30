package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerPrivateViewKeyException.java
 *
 * Could not access the private view key on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerPrivateViewKeyException extends LedgerException {
	public LedgerPrivateViewKeyException() {
		super(
				"Could not access the private view key on the device."
		);
	}
}