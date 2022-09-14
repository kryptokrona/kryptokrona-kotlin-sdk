package org.kryptokrona.sdk.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * TransactionData.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class TransactionData {

	private List<Transaction> transactionToAdd;

	/**
	 * Mapping of public spend key to inputs.
	 */
	private Map<String, TransactionInputImpl> inputsToAdd;

	/**
	 * Mapping of public spend key to key image.
	 */
	private List<Map<String, String>> keyImagesToMarkSpent;
}
