package org.kryptokrona.sdk.crypto;

/**
 * LedgerAPDU.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum LedgerAPDU {
	P2(0x00),
	P1_NON_CONFIRM(0x00),
	P1_CONFIRM(0x01),
	INS(0xe0);

	private int code;

	LedgerAPDU(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
