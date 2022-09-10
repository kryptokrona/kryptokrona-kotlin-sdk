package org.kryptokrona.sdk.util;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.config.Config;
import org.kryptokrona.sdk.config.Constants;
import org.kryptokrona.sdk.wallet.Address;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * CryptoUtils.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class CryptoUtils {

	public static Observable<Map<String, String>> addressToKeys(String address) {
		return Observable.empty();
	}

	public static long getLowerBound(long val, long nearestMultiple) {
		var remainder = val % nearestMultiple;
		return val - remainder;
	}

	public static long getUpperBound(long val, long nearestMultiple) {
		return getLowerBound(val, nearestMultiple) + nearestMultiple;
	}

	public static long getCurrentTimestampAdjusted(long blockTargetTime) {
		var timestamp = Math.floor((double) Instant.now().toEpochMilli() / 1000);
		var currentTimestampAdjusted = timestamp - (100 * blockTargetTime);

		return (long) currentTimestampAdjusted;
	}

	public static boolean isInputUnlocked(long unlockTime, long currenHeight) {
		/* Might as well return fast with the case that is true for nearly all
           transactions (excluding coinbase) */
		if (unlockTime == 0) {
			return true;
		}

		if (unlockTime >= Constants.MAX_BLOCK_NUMBER) {
			return (Math.floor((double) Instant.now().toEpochMilli() / 1000)) >= unlockTime;
		} else {
			return currenHeight + 1 >= unlockTime;
		}
	}

	public static String prettyPrintAmount(double amount) {
		return null;
	}

	public Observable<Void> delay(long ms) {
		return Observable.empty();
	}

	public static List<Long> splitAmountIntoDenominations(double amount, boolean preventTooLargeOutputs) {
		return null;
	}

	/**
	 * The formula for the block size is as follows. Calculate the
	 * maxBlockCumulativeSize. This is equal to:
	 * 100,000 + ((height * 102,400) / 1,051,200)
	 * At a block height of 400k, this gives us a size of 138,964.
	 * The constants this calculation arise from can be seen below, or in
	 * src/CryptoNoteCore/Currency.cpp::maxBlockCumulativeSize(). Call this value
	 * x.
	 *
	 * Next, calculate the median size of the last 100 blocks. Take the max of
	 * this value, and 100,000. Multiply this value by 1.25. Call this value y.
	 *
	 * Finally, return the minimum of x and y.
	 *
	 * Or, in short: min(140k (slowly rising), 1.25 * max(100k, median(last 100 blocks size)))
	 * Block size will always be 125k or greater (Assuming non testnet)
	 *
	 * To get the max transaction size, remove 600 from this value, for the
	 * reserved miner transaction.
	 *
	 * We are going to ignore the median(last 100 blocks size), as it is possible
	 * for a transaction to be valid for inclusion in a block when it is submitted,
	 * but not when it actually comes to be mined, for example if the median
	 * block size suddenly decreases. This gives a bit of a lower cap of max
	 * tx sizes, but prevents anything getting stuck in the pool.
	 *
	 * @param currentHeight The current height to use
	 * @param blockTime The blocktime to use
	 * @return Gets the max transaction size
	 */
	public static long getMaxTxSize(long currentHeight, long blockTime) {
		long numerator = currentHeight * Constants.MAX_BLOCK_SIZE_GROWTH_SPEED_NUMERATOR;
		long denominator = (Constants.MAX_BLOCK_SIZE_GROWTH_SPEED_DENOMINATOR / blockTime);
		long growth = numerator / denominator;
		long x = Constants.MAX_BLOCK_SIZE_INITIAL + growth;
		long y = 125000;

		/* Need space for the miner transaction */
		return Math.min(x, y) - Constants.CRYPTONOTE_COINBASE_BLOB_RESERVED_SIZE;
	}

	/**
	 * Converts an amount in bytes, say, 10000, into 9.76 KB
	 *
	 * @param bytes The amount of bytes to convert
	 * @return Gets the amount in bytes to human readable format
	 */
	public static String prettyPrintBytes(long bytes) {
		return null;
	}

	/**
	 * Returns whether the given word is in the mnemonic english dictionary. Note that
	 * just because all the words are valid, does not mean the mnemonic is valid.
	 *
	 * Use isValidMnemonic to verify that.
	 *
	 * @param word The word to test
	 */
	public static boolean isValidMnemonicWord(String word) {
		return false;
	}

	/**
	 * Verifies whether a mnemonic is valid. Returns a boolean, and an error messsage
	 * describing what is invalid.
	 *
	 * @param mnemonic The mnemonic to verify
	 */
	public static Observable<Map<Boolean, String>> isValidMnemonic(String mnemonic) {
		return Observable.empty();
	}

	public static double getMinimumTransactionFee(long transactionSize, long height) {
		return getTransactionFee(transactionSize, height, Config.MINIMUM_FEE_PER_BYTE);
	}

	public static double getTransactionFee(long transactionSize, long height, double feePerByte) {
		return 0.0;
	}

}
