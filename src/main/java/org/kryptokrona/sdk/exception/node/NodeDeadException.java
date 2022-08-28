package org.kryptokrona.sdk.exception.node;

/**
 * NodeDeadException.java
 * <p>
 * Node Dead Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class NodeDeadException extends NodeException {
	public NodeDeadException() {
		super("Node Dead.");
	}
}
