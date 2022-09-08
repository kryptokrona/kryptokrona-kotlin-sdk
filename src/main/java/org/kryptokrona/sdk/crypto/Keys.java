package org.kryptokrona.sdk.crypto;

import io.reactivex.rxjava3.core.Observable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Keys.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Keys {

	private KeyPair spendKeys;

	private KeyPair viewKeys;

	public Keys(KeyPair spendKeys, KeyPair viewKeys) {
		this.spendKeys = spendKeys;
		this.viewKeys = viewKeys;
	}

	public Observable<Keys> from(KeyPair spendKeys, KeyPair viewKeys) {
		var keys = new Keys();
		keys.setSpendKeys(spendKeys);
		keys.setViewKeys(viewKeys);

		return Observable.just(keys);
	}
}
