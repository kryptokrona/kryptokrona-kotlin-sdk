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
    public WalletUnsupportedFileFormatVersionException() {
        super(
                "This wallet file appears to be from a newer or older " +
                "version of the software, that we do not support."
        );
    }
}
