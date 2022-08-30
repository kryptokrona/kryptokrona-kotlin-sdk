package org.kryptokrona.sdk.model.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionInputImpl;

/**
 * TxInputAndOwner.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class TxInputAndOwner {

	private TransactionInputImpl input;
	private String privateSpendKey;
	private String publicSpendKey;

	public TxInputAndOwner(TransactionInputImpl input, String privateSpendKey, String publicSpendKey) {
		this.input = input;
		this.privateSpendKey = privateSpendKey;
		this.publicSpendKey = publicSpendKey;
	}
}
