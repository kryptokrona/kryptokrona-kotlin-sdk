package org.kryptokrona.sdk.exception.network;

/**
 * NetworkBlockCount.java
 *
 * NetworkBlockCount Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class NetworkBlockCountException extends Exception {
    public NetworkBlockCountException(String errorMessage) {
        super(errorMessage);
    }
}
