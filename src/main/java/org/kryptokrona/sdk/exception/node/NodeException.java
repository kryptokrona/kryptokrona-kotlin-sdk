package org.kryptokrona.sdk.exception.node;

/**
 * NodeException.java
 * <p>
 * Abstract Daemon Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public abstract class NodeException extends Exception {
	public NodeException(String errorMessage) {
		super(errorMessage);
	}
}
