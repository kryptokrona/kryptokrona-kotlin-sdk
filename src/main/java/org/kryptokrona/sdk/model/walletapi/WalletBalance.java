package org.kryptokrona.sdk.model.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WalletBalance {

	/**
	 * the amount of unlocked funds
	 */
	private double unlocked;

	/**
	 * the amount of locked funds
	 */
	private double locked;
}
