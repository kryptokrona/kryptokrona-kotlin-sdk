package org.kryptokrona.sdk.crypto;

/**
 * LedgerTransactionState.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum LedgerTransactionState {
	INACTIVE(0x00),
	READY(0x01),
	RECEIVING_INPUTS(0x02),
	INPUTS_RECEIVED(0x03),
	RECEIVING_OUTPUTS(0x04),
	OUTPUTS_RECEIVED(0x05),
	PREFIX_READY(0x06),
	COMPLETE(0x07);

	private int code;

	LedgerTransactionState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
