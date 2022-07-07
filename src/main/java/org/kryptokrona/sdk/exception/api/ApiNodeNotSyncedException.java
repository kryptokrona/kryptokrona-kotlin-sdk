package org.kryptokrona.sdk.exception.api;

/**
 * ApiNodeNotSyncedException.java
 *
 * The daemon must be synced to use this method.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiNodeNotSyncedException extends ApiException {
    public ApiNodeNotSyncedException() {
        super("Daemon must be synced to process this RPC method call, please retry when synced.");
    }
}
