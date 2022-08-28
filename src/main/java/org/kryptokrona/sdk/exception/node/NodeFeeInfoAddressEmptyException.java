package org.kryptokrona.sdk.exception.node;

/**
 * NodeFeeAddressException.java
 * <p>
 * Node Fee Address Exception.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class NodeFeeInfoAddressEmptyException extends NodeException {
	public NodeFeeInfoAddressEmptyException() {
		super("Node fee address is empty.");
	}
}
