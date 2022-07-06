package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletWrongPasswordException.java
 *
 * Either the AES decryption failed due to wrong padding, or the decrypted
 * data does not have the correct prefix indicating the password is correct.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletWrongPasswordException extends WalletException {
    public WalletWrongPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
