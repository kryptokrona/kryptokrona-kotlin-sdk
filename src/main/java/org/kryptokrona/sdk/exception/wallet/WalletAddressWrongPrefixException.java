package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletAddressWrongPrefixException.java
 *
 * The address does not have the correct prefix, e.g. does not begin with
 * XKR (or whatever is specified in WalletConfig::addressPrefix).
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletAddressWrongPrefixException extends WalletException {
    public WalletAddressWrongPrefixException() {
        super(
                "The address does not have the correct prefix corresponding " +
                "to this coin - it appears to be an address for another " +
                "cryptocurrency."
        );
    }
}
