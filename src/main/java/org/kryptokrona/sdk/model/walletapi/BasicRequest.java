package org.kryptokrona.sdk.model.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * WalletAPI.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class BasicRequest {

	private String destination;

	private double amount;

	private String paymentID;
}
