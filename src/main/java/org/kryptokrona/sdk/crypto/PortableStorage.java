package org.kryptokrona.sdk.crypto;

import java.nio.Buffer;
import java.util.List;
import java.util.Objects;

/**
 * PortableStorage.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class PortableStorage {

	protected int version;

	protected int signatureA;

	protected int signatureB;

	protected List<PortableStorageEntry> entries;

	public PortableStorage() {
		this.version = PortableStorageConstants.VERSION.getCode();
		this.signatureA = PortableStorageConstants.SIGNATURE_A.getCode();
		this.signatureB = PortableStorageConstants.SIGNATURE_B.getCode();
	}

	private boolean exists(String name) {
		for (PortableStorageEntry entry : entries) {
			if (Objects.equals(entry.getName(), name)) {
				return true;
			}
		}

		return false;
	}

	public void toBuffer(boolean skipHeader) {
		// should change return type
	}
}
