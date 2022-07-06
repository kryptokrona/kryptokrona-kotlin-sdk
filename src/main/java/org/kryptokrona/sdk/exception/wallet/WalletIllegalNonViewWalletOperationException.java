package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIllegalNonViewWalletOperationException.java
 *
 * Cannot perform this operation when using a non view wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIllegalNonViewWalletOperationException extends WalletException {
    public WalletIllegalNonViewWalletOperationException(String errorMessage) {
        super(errorMessage);
    }
}
