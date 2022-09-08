package org.kryptokrona.sdk.model.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * TransactionInfo.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class TransactionInfo {

	/**
	 * The block height of the block containing the transaction
	 */
	private long blockheight;

	/**
	 * The network fee of the transaction
	 */
	private double fee;

	/**
	 * The transaction hash
	 */
	private String hash;

	/**
	 * Whether the transaction is a coinbase transaction
	 */
	private boolean isCoinbaseTransaction;

	/**
	 * The payment ID of the transaction, if any
	 */
	private String paymentID;

	/**
	 * The timestamp of the transaction
	 */
	private long timestamp;

	/**
	 * the unlock time of the transaction
	 */
	private long unlockTime;

	/**
	 * a transfer destination object describing where the funds went
	 */
	private List<TransferDestination> transfers;
}
