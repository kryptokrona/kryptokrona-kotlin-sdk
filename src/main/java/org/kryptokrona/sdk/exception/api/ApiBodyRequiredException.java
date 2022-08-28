package org.kryptokrona.sdk.exception.api;

/**
 * ApiBodyRequiredException.java
 * <p>
 * The API requests a body.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiBodyRequiredException extends ApiException {
	public ApiBodyRequiredException() {
		super("This API endpoint requires the submission of a body for processing.");
	}
}
