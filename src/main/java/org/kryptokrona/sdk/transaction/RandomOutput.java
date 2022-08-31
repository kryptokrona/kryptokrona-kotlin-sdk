package org.kryptokrona.sdk.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RandomOutput.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class RandomOutput {

	/**
	 * The output key
	 */
	private String key;

	/**
	 * The output global index
	 */
	private long globalIndex;
}
