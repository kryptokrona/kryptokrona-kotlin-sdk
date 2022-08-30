package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerOperationNotPermittedException.java
 *
 * The user refused the completion of this operation on the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerOperationNotPermittedException extends LedgerException {
	public LedgerOperationNotPermittedException() {
		super("The user refused the completion of this operation on the device.");
	}
}

