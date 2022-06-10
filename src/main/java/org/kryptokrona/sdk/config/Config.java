package org.kryptokrona.sdk.config;

/**
 * Config.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class Config {

    public float                    decimalPlaces;
    public long                     addressPrefix;
    public long                     requestTimeout;
    public long                     blockTargetTime;
    public long                     syncThreadInterval;
    public long                     daemonUpdateInterval;
    public long                     lockedTransactionsCheckInterval;
    public long                     blocksPerTick;
    public boolean                  scanCoinbaseTransactions;
    public long                     minimumFee;

    // public List<MixinLimit>              mixinLimits;

    public long                     standardAddressLength;
    public long                     integratedAddressLength;

    /*public UnderivePublicKey        underivePublicKey;
    public DerivePublicKey          derivePublicKey;
    public DeriveSecretKey          deriveSecretKey;
    public GenerateKeyImage         generateKeyImage;
    public SecretKeyToPublicKey     secretKeyToPublicKey;
    public CnFastHash               cnFastHash;
    public GenerateRingSignatures   generateRingSignatures;
    public CheckRingSignatures      checkRingSignatures;
    public GenerateKeyDerivation    generateKeyDerivation;*/

    public long                     blockStoreMemoryLimit;
    public long                     blocksPerDaemonRequest;
    public long                     maxLastFetchedBlockInterval;
    public long                     maxLastUpdatedNetworkHeightInterval;
    public long                     maxLastUpdatedLocalHeightInterval;

    public String                   customUserAgentString;

    // public LedgerTransport          ledgerTransport;

}
