package org.kryptokrona.sdk.exception.api;

/**
 * ApiBodyRequiredException.java
 *
 * The API requests a body.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiBodyRequiredException extends ApiException {
    public ApiBodyRequiredException(String errorMessage) {
        super(errorMessage);
    }
}
