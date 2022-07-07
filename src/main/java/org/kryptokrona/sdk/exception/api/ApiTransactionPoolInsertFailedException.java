package org.kryptokrona.sdk.exception.api;

/**
 * ApiTransactionPoolInsertFailedException.java
 *
 * Could not add transaction to the transaction pool via API.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiTransactionPoolInsertFailedException extends ApiException {
    public ApiTransactionPoolInsertFailedException() {
        super("Could not add transaction to the transaction pool.");
    }
}
