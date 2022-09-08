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

	public Observable<String> generateKeyDerivation() {
		return Observable.empty();
	}

	public Observable<Map<String, String>> generateKeyImagePrimitive() {
		return Observable.empty();
	}

	public Observable<Map<String, String>> generateKeyImage() {
		return Observable.empty();
	}

	public Observable<String> underivePublicKey() {
		return Observable.empty();
	}
}
