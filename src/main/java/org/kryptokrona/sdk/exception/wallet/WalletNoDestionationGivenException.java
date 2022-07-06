package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNoDestionationGivenException.java
 *
 * The destinations array is empty.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNoDestionationGivenException extends WalletException {
    public WalletNoDestionationGivenException(String errorMessage) {
        super(errorMessage);
    }
}
