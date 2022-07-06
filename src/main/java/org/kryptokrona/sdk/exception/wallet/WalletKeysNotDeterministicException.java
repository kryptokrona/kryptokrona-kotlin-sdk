package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletKeysNotDeterministicException.java
 *
 * View key is not derived from spend key for this address.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletKeysNotDeterministicException extends WalletException {
    public WalletKeysNotDeterministicException(String errorMessage) {
        super(errorMessage);
    }
}
