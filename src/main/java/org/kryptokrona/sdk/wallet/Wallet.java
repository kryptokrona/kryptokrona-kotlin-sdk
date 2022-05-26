package org.kryptokrona.sdk.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Wallet {
    private List<Transaction> transactionList;
}
