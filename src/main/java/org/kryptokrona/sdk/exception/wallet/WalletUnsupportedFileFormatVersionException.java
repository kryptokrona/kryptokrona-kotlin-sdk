package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletUnsupportedFileFormatVersionException.java
 *
 * The wallet file is using a different version than the version supported
 * by this version of the software. (Also could be potential corruption).
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletUnsupportedFileFormatVersionException extends WalletException {
    public WalletUnsupportedFileFormatVersionException(String errorMessage) {
        super(errorMessage);
    }
}
