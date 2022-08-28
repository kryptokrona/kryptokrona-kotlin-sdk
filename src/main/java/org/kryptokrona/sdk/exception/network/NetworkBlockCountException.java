package org.kryptokrona.sdk.exception.network;

/**
 * NetworkBlockCount.java
 * <p>
 * NetworkBlockCount Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class NetworkBlockCountException extends Exception {
	public NetworkBlockCountException() {
		super("Network block count cannot be 0.");
	}
}
