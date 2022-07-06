package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressNotBase58Exception.java
 *
 * The address is not fully comprised of base58 characters.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressNotBase58Exception extends WalletException {
    public WalletAddressNotBase58Exception(String errorMessage) {
        super(errorMessage);
    }
}
