package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidPrivateKeyException.java
 * <p>
 * Not on ed25519 curve.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidPrivateKeyException extends WalletException {
	public WalletInvalidPrivateKeyException() {
		super("The private key given is not a valid ed25519 private key.");
	}
}
