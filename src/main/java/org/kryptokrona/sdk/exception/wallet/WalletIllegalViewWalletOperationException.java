package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIllegalViewWalletOperationException.java
 *
 * Cannot perform this operation when using a view wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIllegalViewWalletOperationException extends WalletException {
	public WalletIllegalViewWalletOperationException() {
		super("This function cannot be called when using a view wallet.");
	}
}
