package org.kryptokrona.sdk.exception.api;

/**
 * ApiBlockNotAcceptedException.java
 *
 * Could not add candidate block to blockchain via API.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiBlockNotAcceptedException extends ApiException {
    public ApiBlockNotAcceptedException(String errorMessage) {
        super(errorMessage);
    }
}
