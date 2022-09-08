package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transfer.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Transfer {

	private String address;

	private String messageId;

	private String payload;
}
