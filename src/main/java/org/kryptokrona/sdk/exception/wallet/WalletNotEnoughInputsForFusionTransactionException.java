package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNotEnoughInputsForFusionTransactionException.java
 * <p>
 * Don't have enough inputs to make a fusion transaction, wallet is fully optimized.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNotEnoughInputsForFusionTransactionException extends WalletException {
	public WalletNotEnoughInputsForFusionTransactionException() {
		super("Cannot send fusion transaction - wallet is already fully optimized.");
	}
}
