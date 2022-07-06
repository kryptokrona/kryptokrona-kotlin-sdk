package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAmountsNotPrettyException.java
 *
 * Amounts not a member of PRETTY_AMOUNTS.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAmountsNotPrettyException extends WalletException {
    public WalletAmountsNotPrettyException(String errorMessage) {
        super(errorMessage);
    }
}
