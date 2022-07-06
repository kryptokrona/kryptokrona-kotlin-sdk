package org.kryptokrona.sdk.exception.api;

/**
 * ApiHashNotFoundException.java
 *
 * Could not find the requested item.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiHashNotFoundException extends ApiException {
    public ApiHashNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
