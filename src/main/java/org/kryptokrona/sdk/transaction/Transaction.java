package org.kryptokrona.sdk.transaction;

import com.google.gson.Gson;

public interface Transaction {

	double totalAmount();

	boolean isFusionTransaction();

	Gson toJson();
}
