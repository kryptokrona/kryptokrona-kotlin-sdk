package org.kryptokrona.sdk.exception.wallet;

/**
 * WalletNotEnoughFakeOutputsException.java
 *
 * We got mixin/fake outputs from the daemon, but not enough. E.g. using a
 * mixin of 3, we only got one fake output -> can't form transaction.
 * This is most likely to be encountered on new networks, where not
 * enough outputs have been created, or if you have a very large output
 * that not enough have been created of.
 *
 * Try resending the transaction with a mixin of zero, if that is an option
 * on your network.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class WalletNotEnoughFakeOutputsException extends WalletException {
    public WalletNotEnoughFakeOutputsException(String errorMessage) {
        super(errorMessage);
    }
}
