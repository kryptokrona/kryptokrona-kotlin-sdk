package org.kryptokrona.sdk.block;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.Transaction;

import java.util.List;

/**
 * ParentBlock.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class ParentBlock {

	/**
	 * The major block version number
	 */
	private int majorVersion;

	/**
	 * The minor block version number
	 */
	private int minorVersion;

	/**
	 * The previous block hash
	 */
	private String previousBlockHash;

	/**
	 * The number of transactions in the block
	 */
	private long transactionCount;

	/**
	 * The base transaction branches
	 */
	private List<String> baseTransactionBranch;

	/**
	 * The miner reward transaction
	 */
	private Transaction minerTransaction;

	/**
	 * The blockchain branches
	 */
	private List<String> blockchainBranch;

	public ParentBlock(
			int majorVersion, int minorVersion, String previousBlockHash, long transactionCount,
			List<String> baseTransactionBranch, Transaction minerTransaction, List<String> blockchainBranch
	) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.previousBlockHash = previousBlockHash;
		this.transactionCount = transactionCount;
		this.baseTransactionBranch = baseTransactionBranch;
		this.minerTransaction = minerTransaction;
		this.blockchainBranch = blockchainBranch;
	}
}
