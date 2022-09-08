package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.Setter;

/**
 * Cache.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class Cache {

	private String addressPrefix;

	private String address;

	public Cache() {
		this.addressPrefix = "";
		this.address = "";
	}
}
