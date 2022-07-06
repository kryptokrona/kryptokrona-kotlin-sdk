package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFilenameNonExistentException.java
 *
 * The wallet filename given does not exist or the program does not have
 * permission to view it.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFilenameNonExistentException extends WalletException {
    public WalletFilenameNonExistentException(String errorMessage) {
        super(errorMessage);
    }
}
