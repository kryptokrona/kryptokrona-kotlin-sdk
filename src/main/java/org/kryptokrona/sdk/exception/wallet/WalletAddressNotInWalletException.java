package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressNotInWalletException.java
 * <p>
 * The address given does not exist in this container, and it's required,
 * for example you specified it as the address to get the balance from.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressNotInWalletException extends WalletException {
	public WalletAddressNotInWalletException() {
		super(
				"The address given does not exist in the wallet container, " +
						"but is required to exist for this operation."
		);
	}
}
