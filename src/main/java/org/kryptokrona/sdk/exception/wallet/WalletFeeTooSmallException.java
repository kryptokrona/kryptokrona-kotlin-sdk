package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFeeTooSmallException.java
 *
 * The fee given is lower than the CryptoNote::parameters::MINIMUM_FEE.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFeeTooSmallException extends WalletException {
    public WalletFeeTooSmallException(String errorMessage) {
        super(errorMessage);
    }
}
