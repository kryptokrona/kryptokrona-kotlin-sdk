package org.kryptokrona.sdk.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TransferDestination.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class TransferDestination {

	/**
	 * The public wallet address of the recipient
	 */
	private String address;

	/**
	 * The atomic amount to send to the recipient
	 */
	private double amount;
}
