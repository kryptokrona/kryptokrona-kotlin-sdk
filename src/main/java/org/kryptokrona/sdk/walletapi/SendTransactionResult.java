package org.kryptokrona.sdk.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SendTransactionResult.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class SendTransactionResult {

	/**
	 * The transaction hash
	 */
	private String transactionHash;

	/**
	 * The network fee paid
	 */
	private double fee;

	/**
	 * Whether the transaction was relayed to the network
	 */
	private boolean relayedToNetwork;
}
