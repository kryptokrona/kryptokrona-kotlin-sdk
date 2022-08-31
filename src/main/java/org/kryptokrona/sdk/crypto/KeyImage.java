package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * KeyImage.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class KeyImage {

	private String keyImage;

	private String publicEphemeral;

	private String privateEphemeral;

	public KeyImage(String keyImage, String publicEphemeral, String privateEphemeral) {
		this.keyImage = keyImage;
		this.publicEphemeral = publicEphemeral;
		this.privateEphemeral = privateEphemeral;
	}

}
