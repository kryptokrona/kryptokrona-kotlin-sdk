package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletConflictingPaymentIdsException.java
 * <p>
 * Conflicting payment ID's were found, due to integrated addresses. These
 * could mean an integrated address + payment ID were given, where they
 * are not the same, or that multiple integrated addresses with different
 * payment IDs were given
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletConflictingPaymentIdsException extends WalletException {
	public WalletConflictingPaymentIdsException() {
		super(
				"Conflicting payment IDs were given. This could mean " +
						"an integrated address + payment ID were given, where " +
						"they are not the same, or that multiple integrated " +
						"addresses with different payment IDs were given."
		);
	}
}
