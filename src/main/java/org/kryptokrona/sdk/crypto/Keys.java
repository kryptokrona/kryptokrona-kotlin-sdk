package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.Setter;

/**
 * Keys.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class Keys {

	private KeyPair mSpendKeys;

	private KeyPair mViewKeys;

	public Keys(KeyPair mSpendKeys, KeyPair mViewKeys) {
		this.mSpendKeys = mSpendKeys;
		this.mViewKeys = mViewKeys;
	}
}
