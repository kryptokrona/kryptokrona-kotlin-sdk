package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAmountIsZeroException.java
 *
 * One of the destination parameters has an amount given of zero.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAmountIsZeroException extends WalletException {
    public WalletAmountIsZeroException(String errorMessage) {
        super(errorMessage);
    }
}
