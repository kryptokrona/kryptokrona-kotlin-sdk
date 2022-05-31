package org.kryptokrona.sdk.http;

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
public class FeeInfo {

    private String address;
    private int amount;
    private String status;

    public FeeInfo(String address, int amount, String status) {
        this.address    = address;
        this.amount     = amount;
        this.status     = status;
    }
}
