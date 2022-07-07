package org.kryptokrona.sdk.model.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UnconfirmedInput.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class UnconfirmedInput {

    private double amount;
    private String key;
    private String parentTransactionHash;

    public UnconfirmedInput(double amount, String key, String parentTransactionHash) {
        this.amount = amount;
        this.key = key;
        this.parentTransactionHash = parentTransactionHash;
    }
}
