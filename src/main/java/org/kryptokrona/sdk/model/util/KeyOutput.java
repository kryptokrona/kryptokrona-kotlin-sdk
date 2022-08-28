package org.kryptokrona.sdk.model.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * KeyOutput.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class KeyOutput {

	private String key;
	private double amount;
	private long globalIndex;

	public KeyOutput(String key, double amount) {
		this.key = key;
		this.amount = amount;
	}
}
