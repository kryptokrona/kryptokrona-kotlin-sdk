package org.kryptokrona.sdk.walletapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StatusInfo.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
@NoArgsConstructor
public class StatusInfo {

	/**
	 * How many blocks the wallet has synced
	 */
	private long walletBlockCount;

	/**
	 * How many blocks the node has synced
	 */
	private long localDaemonBlockCount;

	/**
	 * How many blocks the network has synced
	 */
	private long networkBlockCount;

	/**
	 * The number of peers the node is connected to
	 */
	private long peerCount;

	/**
	 * The current estimated network hashrate
	 */
	private double hashrate;

	/**
	 * Whether the current wallet container is a view-only wallet
	 */
	private boolean isViewWallet;

	/**
	 * How many subwallets exist in the wallet container
	 */
	private long subWalletCount;
}
