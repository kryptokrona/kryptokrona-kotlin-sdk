package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIllegalViewWalletOperation.java
 *
 * Cannot perform this operation when using a view wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIllegalViewWalletOperation extends WalletException {
    public WalletIllegalViewWalletOperation(String errorMessage) {
        super(errorMessage);
    }
}
