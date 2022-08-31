package org.kryptokrona.sdk.config;

/**
 * CoinConfig.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class CoinConfig {

	private final static long ACTIVATE_PARENT_BLOCK_VERSION = 2;

	private final static long COIN_UNIT_PLACES = 5;

	private final static long ADDRESS_PREFIX = 2239254;

	private final static long KECCAK_ITERATIONS = 1;

	private final static double DEFAULT_NETWORK_FEE = 10;

	private final static long FUSION_MIN_INPUT_COUNT = 12;

	private final static long FUSION_MIN_IN_OUT_COUNT_RATIO = 4;

	private final static long MM_MINING_BLOCK_VERSION = 2;

	private final static long MAXIMUM_OUTPUT_AMOUNT = 100000000000L;

	private final static long MAXIMUM_OUTPUTS_PER_TRANSACTION = 90;

	private final static long MAXIMUM_EXTRA_SIZE = 1024;

	private final static boolean ACTIVATE_FEE_PER_BYTE_TRANSACTION = true;

	private final static double FEE_PER_BYTE = 1.953125;

	private final static double FEE_PER_BYTE_CHUNK_SIZE = 256;

	private final static long MAXIMUM_LEDGER_TRANSACTION_SIZE = 38400;

	private final static long MAXIMUM_LEDGER_APDU_PAYLOAD_SIZE = 480;

	private final static String MINIMUM_LEDGER_VERSION = "1.2.0";

	private final static boolean LEDGER_DEBUG = false;
}
