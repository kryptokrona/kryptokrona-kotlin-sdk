package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNotEnoughBalanceException.java
 *
 * Amount + fee is greater than the total balance available in the
 * subwallets specified (or all wallets, if not specified).
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNotEnoughBalanceException extends WalletException {
    public WalletNotEnoughBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
