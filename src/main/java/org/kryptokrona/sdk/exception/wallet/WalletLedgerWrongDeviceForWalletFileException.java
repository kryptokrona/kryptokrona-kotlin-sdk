package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerWrongDeviceForWalletFileException.java
 *
 * Incorrect Ledger wallet connected for this wallet file.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerWrongDeviceForWalletFileException extends WalletException {
    public WalletLedgerWrongDeviceForWalletFileException(String errorMessage) {
        super(errorMessage);
    }
}
