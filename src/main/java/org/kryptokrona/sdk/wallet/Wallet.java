package org.kryptokrona.sdk.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionBasic;

import java.util.List;

/**
 * Wallet.java
 *
 * Represents a Wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

    private List<TransactionBasic> transactionList;
    private double balance;
}
