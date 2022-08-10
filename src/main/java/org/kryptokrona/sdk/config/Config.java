package org.kryptokrona.sdk.config;

import org.kryptokrona.sdk.model.util.MixinLimit;
import org.kryptokrona.sdk.model.util.MixinLimits;

import java.util.Arrays;

/**
 * Config.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class Config {

    /**
     * The amount of decimal places.
     */
    public final static int DECIMAL_PLACES                              = 5;

    /**
     * The address prefix Kryptokrona uses - you can find this in CryptoNoteConfig.h.
     */
    public final static long ADDRESS_PREFIX                             = 2239254;

    /**
     * Request timeout for daemon operations in milliseconds.
     */
    public final static long REQUEST_TIMEOUT                            = 10 * 1000;

    /**
     * The block time of Kryptokrona, in seconds.
     */
    public final static long BLOCK_TARGET_TIME                          = 90;

    /**
     * How often to process blocks, in millseconds.
     */
    public final static long SYNC_THREAD_INTERVAL                       = 10;

    /**
     * How often to update the daemon info.
     */
    public final static long DAEMON_UPDATE_INTERVAL                     = 10 * 1000;

    /**
     * How often to check on locked transactions.
     */
    public final static long LOCKED_TRANSACTIONS_CHECK_INTERVAL         = 30 * 1000;

    /**
     * The amount of blocks to process per 'tick' of the mainloop. Note: too
     * high a value will cause the event loop to be blocked, and your interaction
     * to be laggy.
     */
    public final static long BLOCKS_PER_TICK                            = 1;

    /**
     * Kryptokrona 'ticker'
     */
    public final static String TICKER                                   = "XKR";

    /**
     * Most people haven't mined any blocks, so lets not waste time scanning
     * them.
     */
    public final static boolean SCAN_COINBASE_TRANSACTIONS              = false;

    /**
     * The minimum fee allowed for transactions, in ATOMIC units.
     */
    public final static long MINIMUM_FEE                                = 10;

    /**
     * Fee per byte is rounded up in chunks. This helps makes estimates
     * more accurate. It's suggested to make this a power of two, to relate
     * to the underlying storage cost / page sizes for storing a transaction.
     */
    public final static int FEE_PER_BYTE_CHUNK_SIZE                    = 256;

    /** Fee to charge per byte of transaction. Will be applied in chunks, see
     * above. This value comes out to 1.953125. We use this value instead of
     * something like 2 because it makes for pretty resulting fees
     * - 5 XKR vs 5.12 XKR. You can read this as the fee per chunk
     * is 500 atomic units. The fee per byte is 500 / chunk size.
     */
    public final static double MINIMUM_FEE_PER_BYTE                     = 500.00 / FEE_PER_BYTE_CHUNK_SIZE;

    /**
     * Mapping of height to mixin maximum and mixin minimum.
     */
    public final static MixinLimits MIXIN_LIMITS                        = new MixinLimits(Arrays.asList(
                // Height: 440,000, minMixin: 0, maxMixin: 100, defaultMixin: 3
                new MixinLimit(440000, 0, 100, 3),

                // At height of 620000, static mixin of 7
                new MixinLimit(620000, 7),

                // At height of 800000, static mixin of 3
                new MixinLimit(800000, 3)
            ), 3);

    /**
     * The length of a standard address for Kryptokrona.
     */
    public final static long STANDARD_ADDRESS_LENGTH                    = 99;

    /**
     * The length of an integrated address for Kryptokrona - It's the same as
     * a normal address, but there is a paymentID included in there - since
     * payment ID's are 64 chars, and base58 encoding is done by encoding
     * chunks of 8 chars at once into blocks of 11 chars, we can calculate
     * this automatically.
     */
    public final static long INTEGRATED_ADDRESS_LENGTH                  = 99 + ((64 * 11) / 8);

    /**
     * The amount of memory to use storing downloaded blocks - 50MB
     */
    public final static long BLOCK_STORE_MEMORY_LIMIT                   = 1024 * 1024 * 50;

    /**
     * The amount of blocks to take from the daemon per request. Cannot take
     * more than 100.
     */
    public final static long BLOCKS_PER_DAEMON_REQUEST                  = 100;

    /**
     * The amount of seconds to permit not having fetched a block from the
     * daemon before emitting 'deadnode'. Note that this just means contacting
     * the daemon for data - if you are synced and it returns TopBlock - the
     * event will not be emitted.
     */
    public final static long MAX_LAST_FETCHED_BLOCK_INTERVAL            = 60 * 3;

    /**
     * The amount of seconds to permit not having fetched a new network height
     * from the daemon before emitting 'deadnode'.
     */
    public final static long MAX_LAST_UPDATED_NETWORK_HEIGHT_INTERVAL   = 60 * 3;

    /**
     * The amount of seconds to permit not having fetched a new local height
     * from the daemon before emitting 'deadnode'.
     */
    public final static long MAX_LAST_UPDATED_LOCAL_HEIGHT_INTERVAL     = 60 * 3;

    /**
     * Allows setting a custom user agent string
     */
    public final static String CUSTOM_USER_AGENT_STRING                 = String.format(
            "%s-sdk-%s", TICKER.toLowerCase(), System.getProperty("sdk-version"));

}
