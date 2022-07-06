package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAmountUglyException.java
 *
 * The amount given does not have only a single significant digit - i.e.,
 * it cannot be used directly as a transaction input/output amount.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAmountUglyException extends WalletException {
    public WalletAmountUglyException(String errorMessage) {
        super(errorMessage);
    }
}
