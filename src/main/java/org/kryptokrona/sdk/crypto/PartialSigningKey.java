package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PartialSigningKey.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class PartialSigningKey {

	/**
	 * The transaction prefix hash for which this partial signing key is valid
	 */
	private String transactionPrefixHash;

	/**
	 * The transaction signature index (which signature set this applies to)
	 */
	private long index;

	/**
	 * The partial signing key
	 */
	private String partialSigningKey;
}
