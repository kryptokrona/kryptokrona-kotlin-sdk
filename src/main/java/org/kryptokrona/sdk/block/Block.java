package org.kryptokrona.sdk.block;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionRaw;
import org.kryptokrona.sdk.transaction.TransactionRawCoinbase;

import java.util.List;

/**
 * Block.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Block {

	private TransactionRawCoinbase transactionRawCoinbase;

	private List<TransactionRaw> transactions;

	private long blockHeight;

	private String blockHash;

	private long blockTimestamp;

	public Block(
			TransactionRawCoinbase transactionRawCoinbase, List<TransactionRaw> transactions,
			long blockHeight, String blockHash, long blockTimestamp
	) {
		this.transactionRawCoinbase = transactionRawCoinbase;
		this.transactions = transactions;
		this.blockHeight = blockHeight;
		this.blockHash = blockHash;
		this.blockTimestamp = blockTimestamp;
	}
}
