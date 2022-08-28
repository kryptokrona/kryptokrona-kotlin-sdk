package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletLedgerSubwalletsNotSupportedException.java
 * <p>
 * Ledger based wallets do not currently support subwallets.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletLedgerSubwalletsNotSupportedException extends WalletException {
	public WalletLedgerSubwalletsNotSupportedException() {
		super("Ledger based wallets do not currently support subwallets.");
	}
}
