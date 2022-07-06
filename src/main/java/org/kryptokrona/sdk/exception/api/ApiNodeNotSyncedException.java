package org.kryptokrona.sdk.exception.api;

/**
 * ApiNodeNotSyncedException.java
 *
 * The daemon must be synced to use this method.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiNodeNotSyncedException extends ApiException {
    public ApiNodeNotSyncedException(String errorMessage) {
        super(errorMessage);
    }
}
