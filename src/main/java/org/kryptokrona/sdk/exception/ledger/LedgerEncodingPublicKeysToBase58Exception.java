package org.kryptokrona.sdk.exception.ledger;

/**
 * LedgerEncodingPublicKeysToBase58Exception.java
 *
 * Error encountered encoding the public keys to a Base58 wallet address.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class LedgerEncodingPublicKeysToBase58Exception extends LedgerException {
	public LedgerEncodingPublicKeysToBase58Exception() {
		super(
				"Error encountered encoding the public keys to a Base58 wallet address."
		);
	}
}
