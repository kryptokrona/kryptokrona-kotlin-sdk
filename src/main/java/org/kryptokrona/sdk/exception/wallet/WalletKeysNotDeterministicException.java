package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletKeysNotDeterministicException.java
 *
 * View key is not derived from spend key for this address.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletKeysNotDeterministicException extends WalletException {
    public WalletKeysNotDeterministicException() {
        super(
                "You cannot get a mnemonic seed for this address, as the " +
                "view key is derived in terms of the spend key."
        );
    }
}
