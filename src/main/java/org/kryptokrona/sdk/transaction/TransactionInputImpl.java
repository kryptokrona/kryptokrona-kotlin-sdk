package org.kryptokrona.sdk.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionInputImpl implements TransactionInput {

	private double amount;

	@Override
	public String getKeyImage() {
		return null;
	}

}
