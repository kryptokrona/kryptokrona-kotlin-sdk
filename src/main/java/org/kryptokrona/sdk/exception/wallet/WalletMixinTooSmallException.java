package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletMixinTooSmallException.java
 * <p>
 * The mixin given is too low for the current height known by the wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletMixinTooSmallException extends WalletException {
	public WalletMixinTooSmallException() {
		super(
				"The mixin value given is too low to be accepted by the " +
						"network (based on the current height known by the wallet)"
		);
	}
}
