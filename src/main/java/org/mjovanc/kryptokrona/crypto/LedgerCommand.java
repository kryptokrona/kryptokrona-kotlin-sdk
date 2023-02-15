// Copyright (c) 2022-2022, The Kryptokrona Project
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

package org.mjovanc.kryptokrona.crypto;

/**
 * LedgerCommand.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum LedgerCommand {
	VERSION(0x01),
	DEBUG(0x02),
	IDENT(0x05),
	PUBLIC_KEYS(0x10),
	VIEW_SECRET_KEY(0x11),
	SPEND_SECRET_KEY(0x12),
	WALLET_KEYS(0x13),
	CHECK_KEY(0x16),
	CHECK_SCALAR(0x17),
	PRIVATE_TO_PUBLIC(0x18),
	RANDOM_KEY_PAIR(0x19),
	ADDRESS(0x30),
	GENERATE_KEY_IMAGE(0x40),
	GENERATE_KEY_IMAGE_PRIMITIVE(0x41),
	GENERATE_RING_SIGNATURES(0x50),
	COMPLETE_RING_SIGNATURE(0x51),
	CHECK_RING_SIGNATURES(0x52),
	GENERATE_SIGNATURE(0x55),
	CHECK_SIGNATURE(0x56),
	GENERATE_KEY_DERIVATION(0x60),
	DERIVE_PUBLIC_KEY(0x61),
	DERIVE_SECRET_KEY(0x62),
	TX_STATE(0x70),
	TX_START(0x71),
	TX_START_INPUT_LOAD(0x72),
	TX_LOAD_INPUT(0x73),
	TX_START_OUTPUT_LOAD(0x74),
	TX_LOAD_OUTPUT(0x75),
	TX_FINALIZE_TX_PREFIX(0x76),
	TX_SIGN(0x77),
	TX_DUMP(0x78),
	TX_RESET(0x79),
	RESET_KEYS(0xff);

	private int code;

	LedgerCommand(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
