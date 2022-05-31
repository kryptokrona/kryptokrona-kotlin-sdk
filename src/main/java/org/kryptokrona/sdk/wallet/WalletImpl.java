package org.kryptokrona.sdk.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.TransactionBasic;
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
public class WalletImpl implements Wallet {

    private List<TransactionBasic>  transactionList;
    private double                  balance;

    private static final Logger     logger = LoggerFactory.getLogger(WalletImpl.class);

    public WalletImpl(List<TransactionBasic> transactionList, double balance) {
        this.transactionList    = transactionList;
        this.balance            = balance;
    }

    @Override
    public void start() {
        logger.debug("Starting the wallet sync process.");
    }

    @Override
    public void saveToFile(String fileName, String password) {
        logger.debug("Wallet saved to file.");
    }

    @Override
    public void stop() {
        logger.debug("Stopping the wallet sync process.");
    }
}
