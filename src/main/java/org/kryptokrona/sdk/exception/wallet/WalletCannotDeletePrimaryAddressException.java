package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletCannotDeletePrimaryAddressException.java
 *
 * The primary address cannot be deleted.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletCannotDeletePrimaryAddressException extends WalletException {
    public WalletCannotDeletePrimaryAddressException(String errorMessage) {
        super(errorMessage);
    }
}
