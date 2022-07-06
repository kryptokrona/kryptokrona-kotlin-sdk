package org.kryptokrona.sdk.model.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.model.transaction.TransactionBasic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * WalletImpl.java
 *
 * Represents a Wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class WalletBasic implements Wallet {

    private List<TransactionBasic>  transactionList;
    private double                  balance;

    private static final Logger     logger = LoggerFactory.getLogger(WalletBasic.class);

    public WalletBasic(List<TransactionBasic> transactionList, double balance) {
        this.transactionList    = transactionList;
        this.balance            = balance;
    }
}
