package org.kryptokrona.sdk.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Wallet.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

	/**
	 * The public wallet address
	 */
	private String address;

	/**
	 * The private spend key of the wallet
	 */
	private String privateSpendKey;

	/**
	 * The public spend key of the wallet
	 */
	private String publicSpendKey;

	/**
	 * The deterministic wallet index
	 */
	private long walletIndex;
}
