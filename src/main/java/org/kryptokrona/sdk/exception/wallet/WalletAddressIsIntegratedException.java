package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressIsIntegratedException.java
 * <p>
 * The address is an integrated address - but integrated addresses aren't
 * valid for this parameter, for example, change address.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressIsIntegratedException extends WalletException {
	public WalletAddressIsIntegratedException() {
		super(
				"The address given is an integrated address, but integrated " +
						"addresses aren't valid for this parameter, for example, " +
						"change address."
		);
	}
}
