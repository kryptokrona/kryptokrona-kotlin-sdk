package org.kryptokrona.sdk.exception.api;

/**
 * ApiException.java
 * <p>
 * Abstract Api Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public abstract class ApiException extends Exception {
	public ApiException(String errorMessage) {
		super(errorMessage);
	}
}
