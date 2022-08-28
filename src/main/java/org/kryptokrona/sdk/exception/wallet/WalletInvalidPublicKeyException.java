package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidPublicKeyException.java
 * <p>
 * Not on ed25519 curve.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidPublicKeyException extends WalletException {
	public WalletInvalidPublicKeyException() {
		super("The public key given is not a valid ed25519 public key.");
	}
}
