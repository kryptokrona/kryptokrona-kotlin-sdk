package org.kryptokrona.sdk.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kryptokrona.sdk.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * WalletImpl.java
 * <p>
 * Represents a Wallet.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

	private List<Transaction> transactionList;
	private double balance;

	private static final Logger logger = LoggerFactory.getLogger(Wallet.class);

	public Wallet(List<Transaction> transactionList, double balance) {
		this.transactionList = transactionList;
		this.balance = balance;
	}
}
