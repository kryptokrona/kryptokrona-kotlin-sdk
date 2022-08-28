package org.kryptokrona.sdk.exception.api;

/**
 * ApiBlockExplorerDisabledException.java
 * <p>
 * The API does not have the block explorer enabled.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class ApiBlockExplorerDisabledException extends ApiException {
	public ApiBlockExplorerDisabledException() {
		super(
				"This method has been disabled by the administrator. " +
						"If you are the administrator relaunch your daemon with " +
						"the --enable-blockexplorer command line option to access this method."
		);
	}
}
