package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletTooManyInputsToFitInBlockException.java
 * <p>
 * The transction is too large (in BYTES, not AMOUNT) to fit in a block.
 * Either:
 * 1) decrease the amount you are sending
 * 2) decrease the mixin value
 * 3) split your transaction up into multiple smaller transactions
 * 4) perform fusion transaction to combine multiple small inputs into fewer, larger inputs.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletTooManyInputsToFitInBlockException extends WalletException {
	public WalletTooManyInputsToFitInBlockException() {
		super(
				"The transaction is too large (in BYTES, not AMOUNT) to fit " +
						"in a block. Either decrease the amount you are sending, " +
						"perform fusion transactions, or decrease mixin (if possible)."
		);
	}
}
