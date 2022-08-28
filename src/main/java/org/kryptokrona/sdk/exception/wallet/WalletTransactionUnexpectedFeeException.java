package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletTransactionUnexpectedFeeException.java
 * <p>
 * Transaction fee is not the same as specified fee.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletTransactionUnexpectedFeeException extends WalletException {
	public WalletTransactionUnexpectedFeeException() {
		super(
				"The fee of the created transaction is not the same as that " +
						"which was specified (0 for fusion transactions). Almost " +
						"certainly a programmer error. Cancelling transaction."
		);
	}
}
