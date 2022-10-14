package org.kryptokrona.sdk.crypto;

// import kryptokrona-utils here

import io.reactivex.rxjava3.core.Observable;

import java.util.Map;

/**
 * CryptoWrapper.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class CryptoWrapper {

	public Observable<String> generateKeyDerivation(String transactionPublicKey, String privateViewKey) {
		return Observable.empty();
	}

	public Observable<Map<String, String>> generateKeyImagePrimitive(KeyPair spendKeys, long outputIndex, String derivation) {
		return Observable.empty();
	}

	public Observable<Map<String, String>> generateKeyImage(String transactionPublicKey, String privateViewKey, KeyPair spendKeys, long transactionIndex) {
		return Observable.empty();
	}

	public Observable<String> underivePublicKey(String derivation, long outputIndex) {
		return Observable.empty();
	}
}
