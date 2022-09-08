package org.kryptokrona.sdk.crypto;

/**
 * PortableStorageType.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum PortableStorageType {
	NULL(0x00),
	INT64(0x01),
	INT(0x02),
	INT16(0x03),
	SBYTE(0x04),
	UINT64(0x05),
	UINT32(0x06),
	UINT16(0x07),
	UINT8(0x08),
	DOUBLE(0x09),
	STRING(0x0a),
	BOOL(0x0b),
	OBJECT(0x0c),
	OBJECT_ARRAY_BAD(0x0d),
	BUFFER(0x0e),
	STRING_ARRAY(0x8a),
	OBJECT_ARRAY(0x8c);

	private int code;

	PortableStorageType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
