package org.kryptokrona.sdk.exception.api;

/**
 * ApiBodyRequiredException.java
 *
 * An internal API error occurred.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiInternalErrorException extends ApiException {
    public ApiInternalErrorException() {
        super("An internal error occurred.");
    }
}
