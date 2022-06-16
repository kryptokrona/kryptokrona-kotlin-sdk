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
    private float                    decimalPlaces;

    /**
     * The address prefix Kryptokrona uses - you can find this in CryptoNoteConfig.h.
     */
    private long                     addressPrefix;

    /**
     * Request timeout for daemon operations in milliseconds.
     */
    private long                     requestTimeout;

    /**
     * The block time of Kryptokrona, in seconds.
     */
    private long                     blockTargetTime;

    /**
     * How often to process blocks, in millseconds.
     */
    private long                     syncThreadInterval;

    /**
     * How often to update the daemon info.
     */
    private long                     daemonUpdateInterval;

    /**
     * How often to check on locked transactions.
     */
    private long                     lockedTransactionsCheckInterval;

    /**
     * The amount of blocks to process per 'tick' of the mainloop. Note: too
     * high a value will cause the event loop to be blocked, and your interaction
     * to be laggy.
     */
    private long                     blocksPerTick;

    /**
     * Kryptokrona 'ticker'
     */
    private String                  ticker;

    /**
     * Most people haven't mined any blocks, so lets not waste time scanning
     * them.
     */
    private boolean                  scanCoinbaseTransactions;

    /**
     * The minimum fee allowed for transactions, in ATOMIC units.
     */
    private long                     minimumFee;

    /**
     * Fee per byte is rounded up in chunks. This helps makes estimates
     * more accurate. It's suggested to make this a power of two, to relate
     * to the underlying storage cost / page sizes for storing a transaction.
     */
    private int                      feePerByteChunkSize;

    /** Fee to charge per byte of transaction. Will be applied in chunks, see
     * above. This value comes out to 1.953125. We use this value instead of
     * something like 2 because it makes for pretty resulting fees
     * - 5 XKR vs 5.12 XKR. You can read this as the fee per chunk
     * is 500 atomic units. The fee per byte is 500 / chunk size.
     */
    private double                   minimumFeePerByte;

    /**
     * Mapping of height to mixin maximum and mixin minimum.
     */
    public MixinLimits               mixinLimits;

    /**
     * The length of a standard address for Kryptokrona.
     */
    private long                     standardAddressLength;

    /**
     * The length of an integrated address for Kryptokrona - It's the same as
     * a normal address, but there is a paymentID included in there - since
     * payment ID's are 64 chars, and base58 encoding is done by encoding
     * chunks of 8 chars at once into blocks of 11 chars, we can calculate
     * this automatically.
     */
    private long                     integratedAddressLength;

    /**
     * The amount of memory to use storing downloaded blocks - 50MB
     */
    private long                     blockStoreMemoryLimit;

    /**
     * The amount of blocks to take from the daemon per request. Cannot take
     * more than 100.
     */
    private long                     blocksPerDaemonRequest;

    /**
     * The amount of seconds to permit not having fetched a block from the
     * daemon before emitting 'deadnode'. Note that this just means contacting
     * the daemon for data - if you are synced and it returns TopBlock - the
     * event will not be emitted.
     */
    private long                     maxLastFetchedBlockInterval;

    /**
     * The amount of seconds to permit not having fetched a new network height
     * from the daemon before emitting 'deadnode'.
     */
    private long                     maxLastUpdatedNetworkHeightInterval;

    /**
     * The amount of seconds to permit not having fetched a new local height
     * from the daemon before emitting 'deadnode'.
     */
    private long                     maxLastUpdatedLocalHeightInterval;

    /**
     * Allows setting a custom user agent string
     */
    private String                   customUserAgentString;

    public Config() {
        this.decimalPlaces                          = 5;
        this.addressPrefix                          = 2239254;
        this.requestTimeout                         = 10 * 1000;
        this.blockTargetTime                        = 90;
        this.syncThreadInterval                     = 10;
        this.daemonUpdateInterval                   = 10 * 1000;
        this.lockedTransactionsCheckInterval        = 30 * 1000;
        this.blocksPerTick                          = 1;
        this.ticker                                 = "XKR";
        this.scanCoinbaseTransactions               = false;
        this.minimumFee                             = 10;
        this.feePerByteChunkSize                    = 256;
        this.minimumFeePerByte                      = 500.00 / this.feePerByteChunkSize;
        this.mixinLimits                            = new MixinLimits(Arrays.asList(
                // Height: 440,000, minMixin: 0, maxMixin: 100, defaultMixin: 3
                new MixinLimit(440000, 0, 100, 3),

                // At height of 620000, static mixin of 7
                new MixinLimit(620000, 7),

                // At height of 800000, static mixin of 3
                new MixinLimit(800000, 3)
        ), 3);
        this.standardAddressLength                  = 99;
        this.integratedAddressLength                = 99 + ((64 * 11) / 8);
        this.blockStoreMemoryLimit                  = 1024 * 1024 * 50;
        this.blocksPerDaemonRequest                 = 100;
        this.maxLastFetchedBlockInterval            = 60 * 3;
        this.maxLastUpdatedNetworkHeightInterval    = 60 * 3;
        this.maxLastUpdatedLocalHeightInterval      = 60 * 3;
        this.customUserAgentString                  = String.format("%s-sdk-%s", this.ticker.toLowerCase(), System.getProperty("sdk-version"));
    }
}
