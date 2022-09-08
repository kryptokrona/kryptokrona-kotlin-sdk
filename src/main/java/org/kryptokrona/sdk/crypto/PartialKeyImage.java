package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PartialKeyImage.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class PartialKeyImage {

	/**
	 * The partial key image
	 */
	private String partialKeyImage;

	/**
	 * The transaction hash for which the partial key image belongs
	 */
	private String transactionHash;

	/**
	 * The index in the transaction outputs for which the partial key image belongs
	 */
	private long outputIndex;
}
