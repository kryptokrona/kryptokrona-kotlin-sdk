package org.kryptokrona.sdk.exception.api;

/**
 * ApiBlockExplorerDisabledException.java
 *
 * The API does not have the block explorer enabled.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiBlockExplorerDisabledException extends ApiException {
    public ApiBlockExplorerDisabledException(String errorMessage) {
        super(errorMessage);
    }
}
