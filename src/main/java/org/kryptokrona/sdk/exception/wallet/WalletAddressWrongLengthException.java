package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressWrongLengthException.java
 *
 * The address is the wrong length - neither a standard, nor an integrated address.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressWrongLengthException extends WalletException {
    public WalletAddressWrongLengthException(String errorMessage) {
        super(errorMessage);
    }
}
