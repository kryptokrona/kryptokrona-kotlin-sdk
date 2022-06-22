package org.kryptokrona.sdk.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * Config.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
@Getter
@Setter
public class Config {

    /**
     * The amount of decimal places.
     */
    private final int decimalPlaces                          = 5;

    /**
     * The address prefix Kryptokrona uses - you can find this in CryptoNoteConfig.h.
     */
    private final long addressPrefix                         = 2239254;

    /**
     * Request timeout for daemon operations in milliseconds.
     */
    private final long requestTimeout                        = 10 * 1000;

    /**
     * The block time of Kryptokrona, in seconds.
     */
    private final long blockTargetTime                       = 90;

    /**
     * How often to process blocks, in millseconds.
     */
    private final long syncThreadInterval                    = 10;

    /**
     * How often to update the daemon info.
     */
    private final long daemonUpdateInterval                  = 10 * 1000;

    /**
     * How often to check on locked transactions.
     */
    private final long lockedTransactionsCheckInterval       = 30 * 1000;

    /**
     * The amount of blocks to process per 'tick' of the mainloop. Note: too
     * high a value will cause the event loop to be blocked, and your interaction
     * to be laggy.
     */
    private final long blocksPerTick                         = 1;

    /**
     * Kryptokrona 'ticker'
     */
    private final String ticker                              = "XKR";

    /**
     * Most people haven't mined any blocks, so lets not waste time scanning
     * them.
     */
    private final boolean scanCoinbaseTransactions           = false;

    /**
     * The minimum fee allowed for transactions, in ATOMIC units.
     */
    private final long minimumFee                            = 10;

    /**
     * Fee per byte is rounded up in chunks. This helps makes estimates
     * more accurate. It's suggested to make this a power of two, to relate
     * to the underlying storage cost / page sizes for storing a transaction.
     */
    private final int feePerByteChunkSize                    = 256;

    /** Fee to charge per byte of transaction. Will be applied in chunks, see
     * above. This value comes out to 1.953125. We use this value instead of
     * something like 2 because it makes for pretty resulting fees
     * - 5 XKR vs 5.12 XKR. You can read this as the fee per chunk
     * is 500 atomic units. The fee per byte is 500 / chunk size.
     */
    private final double minimumFeePerByte                   = 500.00 / feePerByteChunkSize;

    /**
     * Mapping of height to mixin maximum and mixin minimum.
     */
    public final MixinLimits mixinLimits                     = new MixinLimits(Arrays.asList(
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
    private final long standardAddressLength                 = 99;

    /**
     * The length of an integrated address for Kryptokrona - It's the same as
     * a normal address, but there is a paymentID included in there - since
     * payment ID's are 64 chars, and base58 encoding is done by encoding
     * chunks of 8 chars at once into blocks of 11 chars, we can calculate
     * this automatically.
     */
    private final long integratedAddressLength               = 99 + ((64 * 11) / 8);

    /**
     * The amount of memory to use storing downloaded blocks - 50MB
     */
    private final long blockStoreMemoryLimit                 = 1024 * 1024 * 50;

    /**
     * The amount of blocks to take from the daemon per request. Cannot take
     * more than 100.
     */
    private final long blocksPerDaemonRequest                = 100;

    /**
     * The amount of seconds to permit not having fetched a block from the
     * daemon before emitting 'deadnode'. Note that this just means contacting
     * the daemon for data - if you are synced and it returns TopBlock - the
     * event will not be emitted.
     */
    private final long maxLastFetchedBlockInterval           = 60 * 3;

    /**
     * The amount of seconds to permit not having fetched a new network height
     * from the daemon before emitting 'deadnode'.
     */
    private final long maxLastUpdatedNetworkHeightInterval   = 60 * 3;

    /**
     * The amount of seconds to permit not having fetched a new local height
     * from the daemon before emitting 'deadnode'.
     */
    private final long maxLastUpdatedLocalHeightInterval     = 60 * 3;

    /**
     * Allows setting a custom user agent string
     */
    private final String customUserAgentString               = String.format(
            "%s-sdk-%s", ticker.toLowerCase(), System.getProperty("sdk-version"));

}
