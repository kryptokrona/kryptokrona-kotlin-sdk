package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PublicSpendKey.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class PublicSpendKey {

	private String key;

	private String signature;
}
