package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerWrongDeviceForWalletFileException.java
 * <p>
 * Incorrect Ledger wallet connected for this wallet file.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerWrongDeviceForWalletFileException extends WalletException {
	public WalletLedgerWrongDeviceForWalletFileException() {
		super("Incorrect ledger wallet connected for this wallet file.");
	}
}
