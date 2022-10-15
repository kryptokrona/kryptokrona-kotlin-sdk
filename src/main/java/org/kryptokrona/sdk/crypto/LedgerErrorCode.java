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

package org.kryptokrona.sdk.crypto;

/**
 * LedgerErrorCode.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum LedgerErrorCode {
	OK(0x9000),
	OP_OK(0x0000),
	OP_NOK(0x0001),
	ERR_OP_NOT_PERMITTED(0x4000),
	ERR_OP_USER_REQUIRED(0x4001),
	ERR_WRONG_INPUT_LENGTH(0x4002),
	ERR_NVRAM_READ(0x4003),
	ERR_UNKNOWN_ERROR(0x4444),
	ERR_VARINT_DATA_RANGE(0x6000),
	ERR_OUT_OF_RANGE(0x6001),
	ERR_TRANSACTION_STATE(0x6500),
	ERR_TX_RESET(0x6501),
	ERR_TX_START(0x6502),
	ERR_TX_LOAD_INPUT(0x6503),
	ERR_TX_LOAD_OUTPUT(0x6504),
	ERR_TX_SIGN(0x6505),
	ERR_TX_INPUT_OUTPUT_OUT_OF_RANGE(0x6506),
	ERR_TX_FINALIZE_PREFIX(0x6507),
	ERR_TX_DUMP(0x6508),
	ERR_TX_INIT(0x6509),
	ERR_TX_AMOUNT(0x6510),
	ERR_PRIVATE_SPEND(0x9400),
	ERR_PRIVATE_VIEW(0x9401),
	ERR_RESET_KEYS(0x9402),
	ERR_ADDRESS(0x9450),
	ERR_BASE58(0x9451),
	ERR_KEY_DERIVATION(0x9500),
	ERR_DERIVE_PUBKEY(0x9501),
	ERR_PUBKEY_MISMATCH(0x9502),
	ERR_DERIVE_SECKEY(0x9503),
	ERR_KECCAK(0x9504),
	ERR_COMPLETE_RING_SIG(0x9505),
	ERR_GENERATE_KEY_IMAGE(0x9506),
	ERR_SECKEY_TO_PUBKEY(0x9507),
	ERR_GENERATE_RING_SIGS(0x9508),
	ERR_GENERATE_SIGNATURE(0x9509),
	ERR_PRIVATE_TO_PUBLIC(0x9510),
	ERR_INPUT_NOT_IN_SET(0x9511),
	ERR_NOT_PUBLIC_KEY(0x9512),
	ERR_CHECK_RING_SIGS(0x9513),
	ERR_GE_FROMBYTES_VARTIME(0x9514),
	ERR_DERIVATION_TO_SCALAR(0x9515),
	ERR_GE_SCALARMULT(0x9516),
	ERR_CHECK_KEY(0x9517),
	ERR_CHECK_SCALAR(0x9518),
	ERR_CHECK_SIGNATURE(0x9519);

	private int code;

	LedgerErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
