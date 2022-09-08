package org.kryptokrona.sdk.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * KeyInput.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class KeyInput implements TransactionInput {

	private String key;
	private double amount;
	private long globalIndex;

	public KeyInput(String key, double amount) {
		this.key = key;
		this.amount = amount;
	}
}
