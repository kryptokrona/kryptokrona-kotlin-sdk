package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAmountsNotPrettyException.java
 *
 * Amounts not a member of PRETTY_AMOUNTS.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAmountsNotPrettyException extends WalletException {
    public WalletAmountsNotPrettyException() {
        super(
                "The created transaction isn't comprised of only 'Pretty' " +
                "amounts. This will cause the outputs to be unmixable. " +
                "Almost certainly a programmer error. Cancelling transaction."
        );
    }
}
