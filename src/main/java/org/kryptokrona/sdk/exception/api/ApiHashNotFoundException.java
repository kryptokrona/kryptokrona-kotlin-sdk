package org.kryptokrona.sdk.exception.api;

/**
 * ApiHashNotFoundException.java
 * <p>
 * Could not find the requested item.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiHashNotFoundException extends ApiException {
	public ApiHashNotFoundException() {
		super("The requested hash could not be found.");
	}
}
