package org.kryptokrona.sdk.model.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.model.transaction.TransactionInput;

/**
 * TxInputAndOwner.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class TxInputAndOwner {

    private TransactionInput    input;
    private String              privateSpendKey;
    private String              publicSpendKey;

    public TxInputAndOwner(TransactionInput input, String privateSpendKey, String publicSpendKey) {
        this.input = input;
        this.privateSpendKey = privateSpendKey;
        this.publicSpendKey = publicSpendKey;
    }
}
