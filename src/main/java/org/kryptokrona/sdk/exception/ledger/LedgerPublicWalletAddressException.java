package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerPublicWalletAddressException.java
 *
 * Could not access the public wallet address of the device.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerPublicWalletAddressException extends LedgerException {
	public LedgerPublicWalletAddressException() {
		super(
				"Could not access the public wallet address of the device."
		);
	}
}