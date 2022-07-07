package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletIllegalNonViewWalletOperationException.java
 *
 * Cannot perform this operation when using a non view wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletIllegalNonViewWalletOperationException extends WalletException {
    public WalletIllegalNonViewWalletOperationException() {
        super("This function can only be used when using a view wallet.");
    }
}
