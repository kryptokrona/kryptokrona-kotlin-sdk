package org.kryptokrona.sdk.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Transactions.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Transactions {

    private List<Integer>       missedTx;
    private String              status;
    private List<Integer>       txsAsHex;

    public Transactions(List<Integer> missedTx, String status, List<Integer> txsAsHex) {
        this.missedTx   = missedTx;
        this.status     = status;
        this.txsAsHex   = txsAsHex;
    }
}
