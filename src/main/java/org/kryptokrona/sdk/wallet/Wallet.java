package org.kryptokrona.sdk.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionBasic;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Wallet {
    private List<TransactionBasic> transactionList;
    private double balance;
}
