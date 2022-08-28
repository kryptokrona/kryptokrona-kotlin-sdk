package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIllegalViewWalletOperation.java
 * <p>
 * Cannot perform this operation when using a view wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIllegalViewWalletOperation extends WalletException {
	public WalletIllegalViewWalletOperation() {
		super("This function cannot be called when using a view wallet.");
	}
}
