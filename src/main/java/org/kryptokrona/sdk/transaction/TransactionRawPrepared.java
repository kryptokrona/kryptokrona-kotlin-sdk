package org.kryptokrona.sdk.transaction;

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

	@Override
	public long getUnlockTime() {
		return 0;
	}

	@Override
	public String getHash() {
		return null;
	}

	@Override
	public long getBlockHeight() {
		return 0;
	}
}
