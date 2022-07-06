package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletInvalidExtraDataException.java
 *
 * Extra data for transaction is not a valid hexadecimal string.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletInvalidExtraDataException extends WalletException {
    public WalletInvalidExtraDataException(String errorMessage) {
        super(errorMessage);
    }
}
