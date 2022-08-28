package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFileCorruptedException.java
 * <p>
 * The file has the correct wallet file prefix, but is corrupted in some
 * other way, such as a missing IV.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFileCorruptedException extends WalletException {
	public WalletFileCorruptedException() {
		super("This wallet file appears to have gotten corrupted.");
	}
}
