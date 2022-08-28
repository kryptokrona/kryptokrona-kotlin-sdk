package org.kryptokrona.sdk.model.transaction;

import com.google.gson.Gson;

public interface Transaction {

	double totalAmount();

	boolean isFusionTransaction();

	Gson toJson();
}
