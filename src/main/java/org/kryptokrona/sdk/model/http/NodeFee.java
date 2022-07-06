package org.kryptokrona.sdk.model.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * FeeInfo.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class NodeFee {

    private String address;
    private int amount;
    private String status;

    public NodeFee(String address, int amount, String status) {
        this.address    = address;
        this.amount     = amount;
        this.status     = status;
    }
}
