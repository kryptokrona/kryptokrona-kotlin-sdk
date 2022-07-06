package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidFilenameException.java
 *
 * The output filename was unable to be opened for saving, probably due
 * to invalid characters.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidFilenameException extends WalletException {
    public WalletInvalidFilenameException(String errorMessage) {
        super(errorMessage);
    }
}
