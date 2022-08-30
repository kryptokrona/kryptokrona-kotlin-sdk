package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerTransactionSignException.java
 *
 * Could not sign the transaction on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerTransactionSignException extends LedgerException {
	public LedgerTransactionSignException() {
		super(
				"Could not sign the transaction on the device."
		);
	}
}