package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressNotValidException.java
 *
 * The address is invalid for some other reason (possibly checksum).
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressNotValidException extends WalletException {
    public WalletAddressNotValidException(String errorMessage) {
        super(errorMessage);
    }
}
