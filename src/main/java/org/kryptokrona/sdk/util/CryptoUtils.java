package org.kryptokrona.sdk.util;

import io.reactivex.rxjava3.core.Observable;
import org.kryptokrona.sdk.config.Constants;
import org.kryptokrona.sdk.wallet.Address;

import java.time.Instant;
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

	public static long getMaxTxSize(long currentHeight, long blockTime) {
		long numerator = currentHeight * Constants.MAX_BLOCK_SIZE_GROWTH_SPEED_NUMERATOR;
		long denominator = (Constants.MAX_BLOCK_SIZE_GROWTH_SPEED_DENOMINATOR / blockTime);
		long growth = numerator / denominator;
		long x = Constants.MAX_BLOCK_SIZE_INITIAL + growth;
		long y = 125000;

		/* Need space for the miner transaction */
		return Math.min(x, y) - Constants.CRYPTONOTE_COINBASE_BLOB_RESERVED_SIZE;
	}
}
