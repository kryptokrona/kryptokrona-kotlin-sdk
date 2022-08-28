package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressNotValidException.java
 * <p>
 * The address is invalid for some other reason (possibly checksum).
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressNotValidException extends WalletException {
	public WalletAddressNotValidException() {
		super(
				"The address given is not valid. Possibly invalid checksum. " +
						"Most likely a typo."
		);
	}
}
