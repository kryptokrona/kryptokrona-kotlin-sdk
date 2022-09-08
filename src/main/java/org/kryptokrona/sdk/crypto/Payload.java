package org.kryptokrona.sdk.crypto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionPrepared;

import java.util.List;

/**
 * Payload.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Payload {

	private List<PublicSpendKey> publicSpendKeys;

	private String privateViewKey;

	private List<PartialKeyImage> partialKeyImages;

	private List<PartialSigningKey> partialSigningKeys;

	private List<TransactionPrepared> preparedTransactions;
}
