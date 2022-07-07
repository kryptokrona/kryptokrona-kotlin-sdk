package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletCannotDeletePrimaryAddressException.java
 *
 * The primary address cannot be deleted.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletCannotDeletePrimaryAddressException extends WalletException {
    public WalletCannotDeletePrimaryAddressException() {
        super("Each wallet has a primary address when created, this address cannot be removed.");
    }
}
