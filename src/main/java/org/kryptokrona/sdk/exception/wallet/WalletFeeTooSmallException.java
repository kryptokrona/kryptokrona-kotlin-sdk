package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFeeTooSmallException.java
 * <p>
 * The fee given is lower than the CryptoNote::parameters::MINIMUM_FEE.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFeeTooSmallException extends WalletException {
	public WalletFeeTooSmallException() {
		super(
				"The fee given for this transaction is below the minimum " +
						"allowed network fee."
		);
	}
}
