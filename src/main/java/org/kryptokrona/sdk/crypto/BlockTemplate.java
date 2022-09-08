package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.Setter;
import org.kryptokrona.sdk.block.Block;
import org.kryptokrona.sdk.transaction.Transaction;

import java.nio.Buffer;

/**
 * BlockTemplate.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class BlockTemplate {

	protected Buffer blockTemplate;
	protected int difficulty;
	protected long height;
	protected long reservedOffset;
	protected Block block;
	protected long extraNonceOffset;
	protected long extraNonceSafeLength;

	public BlockTemplate() {
		// this.blockTemplate = Buffer.alloc(0)
		this.difficulty = 1;
		this.height = 0;
		this.reservedOffset = 0;
		this.block = new Block();
		this.extraNonceOffset = 0;
		this.extraNonceSafeLength = 0;
	}

	public long getActivateParentBlockVersion() {
		return 0;
	}

	public void setActivateParentBlockVersion(long value) {
		
	}

	/**
	 * The original block template in hexadecimal (blob)
	 */
	public String getBlockTemplate() {
		return "";
	}

	public long getBlockNonce() {
		return 0;
	}

	public void setBlockNonce(long value) {

	}

	/**
	 * The miner transaction defined in the block of the block template
	 */
	public Transaction minerTransaction() {
		return null;
	}

	/**
	 * The extra miner nonce typically used for pool based mining
	 */
	public long getMinerNonce(long nonce) {
		return 0;
	}

	public void setMinerNonce() {

	}

	public boolean hashMeetsDifficulty(String hash, long difficulty) {
		return false;
	}

	// convert
	// construct

	// and so on, check BlockTemplate in kryptokrona-utils - not done

	private enum Sizes {
		KEY(32),
		CHECKSUM(4);

		private int code;

		Sizes(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
}
