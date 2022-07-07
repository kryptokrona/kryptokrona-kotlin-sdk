package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletUnknownErrorException.java
 *
 * An unknown error occured.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletUnknownErrorException extends WalletException {
    public WalletUnknownErrorException() {
        super("An unknown error occurred.");
    }
}
