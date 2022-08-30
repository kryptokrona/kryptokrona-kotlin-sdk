package org.kryptokrona.sdk.exception.model.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * WalletSyncData.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class WalletSyncData {

	private double blockCount;

	private ArrayList<String> checkpoints;

	private boolean skipCoinbaseTransactions;

	private long height;

	private long timestamp;
}
