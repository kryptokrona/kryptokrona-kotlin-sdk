package org.kryptokrona.sdk.wallet;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.Map;

public class SubWallets {

	public Observable<Map<Double, Double>> getBalance(long currentHeight, List<String> subWalletsToTakeFrom) {
		return Observable.empty();
	}
}
