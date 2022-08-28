package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletTransactionPrivateKeyNotFoundException.java
 * <p>
 * Couldn't find the private key for this hash.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletTransactionPrivateKeyNotFoundException extends WalletException {
	public WalletTransactionPrivateKeyNotFoundException() {
		super(
				"Couldn't find the private key for this transaction. The " +
						"transaction must exist, and have been sent by this program. " +
						"Transaction private keys cannot be found upon rescanning/" +
						"reimporting."
		);
	}
}
