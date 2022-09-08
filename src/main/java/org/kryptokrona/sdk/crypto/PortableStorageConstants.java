package org.kryptokrona.sdk.crypto;

/**
 * PortableStorageConstants.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum PortableStorageConstants {
	SIGNATURE_A(0x01011101),
	SIGNATURE_B(0x01020101),
	VERSION(1);

	private int code;

	PortableStorageConstants(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
