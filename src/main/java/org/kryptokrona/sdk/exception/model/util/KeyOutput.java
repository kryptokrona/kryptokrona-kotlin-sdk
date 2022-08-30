package org.kryptokrona.sdk.exception.model.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionOutput;

/**
 * KeyOutput.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class KeyOutput implements TransactionOutput {

	private String key;
	private double amount;
	private long globalIndex;

	public KeyOutput(String key, double amount) {
		this.key = key;
		this.amount = amount;
	}
}
