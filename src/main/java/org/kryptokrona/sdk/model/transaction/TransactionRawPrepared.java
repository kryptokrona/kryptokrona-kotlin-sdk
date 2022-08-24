package org.kryptokrona.sdk.model.transaction;

import com.google.gson.Gson;

public class TransactionRawPrepared implements Transaction {
	@Override
	public double totalAmount() {
		return 0;
	}

	@Override
	public boolean isFusionTransaction() {
		return false;
	}

	@Override
	public Gson toJson() {
		return null;
	}
}
