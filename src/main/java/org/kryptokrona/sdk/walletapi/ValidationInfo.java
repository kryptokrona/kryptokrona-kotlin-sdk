package org.kryptokrona.sdk.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ValidationInfo.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class ValidationInfo {

	/**
	 * Whether the address is an integrated address
	 */
	private boolean isIntegrated;

	/**
	 * the payment ID if the address is an integrated address
	 */
	private String paymentID;

	/**
	 * the wallet address supplied
	 */
	private String actualAddress;

	/**
	 * the public spend key of the address
	 */
	private String publicSpendKey;

	/**
	 * the public view key of the address
	 */
	private String publicViewKey;
}
