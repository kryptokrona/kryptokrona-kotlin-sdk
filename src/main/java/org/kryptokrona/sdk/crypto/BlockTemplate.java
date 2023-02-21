// Copyright (c) 2022-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.Setter;
import org.kryptokrona.sdk.model.http.Block;
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
