package org.kryptokrona.sdk.exception.api;

/**
 * ApiInvalidArgumentsException.java
 *
 * An argument supplied to the API endpoint is invalid.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiInvalidArgumentsException extends ApiException {
    public ApiInvalidArgumentsException() {
        super("An argument supplied could not be properly decoded.");
    }
}
