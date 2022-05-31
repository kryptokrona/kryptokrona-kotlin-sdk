package org.kryptokrona.sdk.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionBasic;

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
public class WalletImpl implements Wallet {

    private List<TransactionBasic>  transactionList;
    private double                  balance;

    public WalletImpl(List<TransactionBasic> transactionList, double balance) {
        this.transactionList    = transactionList;
        this.balance            = balance;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveToFile(String fileName, String password) {

    }

    @Override
    public void stop() {

    }
}
