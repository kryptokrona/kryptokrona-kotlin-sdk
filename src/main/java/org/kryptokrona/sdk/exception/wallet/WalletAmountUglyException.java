package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAmountUglyException.java
 * <p>
 * The amount given does not have only a single significant digit - i.e.,
 * it cannot be used directly as a transaction input/output amount.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAmountUglyException extends WalletException {
	public WalletAmountUglyException() {
		super(
				"The amount given does not have only a single significant digit. " +
						"For example, 20000 or 100000 would be fine, but 20001 or 123456 would not."
		);
	}
}
