package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletFailedToCreateRingSignatureException.java
 *
 * Something went wrong creating the ring signatures. Probably a programmer error.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletFailedToCreateRingSignatureException extends WalletException {
    public WalletFailedToCreateRingSignatureException() {
        super(
                "Failed to create ring signature - probably a programmer " +
                "error, or a corrupted wallet."
        );
    }
}
