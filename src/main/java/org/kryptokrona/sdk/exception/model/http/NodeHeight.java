package org.kryptokrona.sdk.exception.model.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Height.java
 * <p>
 * Represents a model to store information about the
 * height of the daemon and the network.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class NodeHeight {

	private int height;
	private int networkHeight;
	private String status;

	public NodeHeight(int height, int networkHeight, String status) {
		this.height = height;
		this.networkHeight = networkHeight;
		this.status = status;
	}
}
