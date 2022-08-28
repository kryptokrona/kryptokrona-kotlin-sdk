package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletOutputDecompositionException.java
 * <p>
 * The transaction has more outputs than are permitted for the number
 * inputs that have been provided
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletOutputDecompositionException extends WalletException {
	public WalletOutputDecompositionException() {
		super(
				"The transaction contains more outputs than what is permitted " +
						"by the number of inputs that have been supplied for the " +
						"transaction. Please try to send your transaction again. " +
						"If the problem persists, please reduce the number of " +
						"destinations that you are trying to send to."
		);
	}
}
