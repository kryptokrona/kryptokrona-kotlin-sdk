package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidGeneratedKeyimageException.java
 *
 * The key image generated was not valid. This is most likely a programmer error.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidGeneratedKeyimageException extends WalletException {
    public WalletInvalidGeneratedKeyimageException(String errorMessage) {
        super(errorMessage);
    }
}
