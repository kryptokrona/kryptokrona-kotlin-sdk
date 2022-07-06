package org.kryptokrona.sdk.exception.network;

/**
 * NetworkException.java
 *
 * Abstract Daemon Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public abstract class NetworkException extends Exception {
    public NetworkException(String errorMessage) {
        super(errorMessage);
    }
}
