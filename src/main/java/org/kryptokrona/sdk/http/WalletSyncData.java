package org.kryptokrona.sdk.http;

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

    private long blockCount;

    private ArrayList<String> checkpoints;

    private boolean skipCoinbaseTransactions;

    private long height;

    private long timestamp;
}
