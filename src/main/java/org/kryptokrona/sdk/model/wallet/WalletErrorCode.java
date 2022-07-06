package org.kryptokrona.sdk.model.wallet;


/**
 * WalletErrorCode.java
 *
 * Possible Error Codes.
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public enum WalletErrorCode {
    
    /**
     * Payment ID is not 64 chars.
     */
    PAYMENT_ID_WRONG_LENGTH(23),

    /**
     * The payment ID is not hex.
     */
    PAYMENT_ID_INVALID(24),

    /**
     * The address is an integrated address - but integrated addresses aren't
     * valid for this parameter, for example, change address.
     */
    ADDRESS_IS_INTEGRATED(25),

    /**
     * Conflicting payment ID's were found, due to integrated addresses. These
     * could mean an integrated address + payment ID were given, where they
     * are not the same, or that multiple integrated addresses with different
     * payment IDs were given
     */
    CONFLICTING_PAYMENT_IDS(26),

    /**
     * Can't get mixin/fake outputs from the daemon, and mixin is not zero.
     */
    CANT_GET_FAKE_OUTPUTS(27),

    /**
     * We got mixin/fake outputs from the daemon, but not enough. E.g. using a
     * mixin of 3, we only got one fake output -> can't form transaction.
     * This is most likely to be encountered on new networks, where not
     * enough outputs have been created, or if you have a very large output
     * that not enough have been created of.
     *
     * Try resending the transaction with a mixin of zero, if that is an option
     * on your network.
     */
    NOT_ENOUGH_FAKE_OUTPUTS(28),

    /**
     * The key image generated was not valid. This is most likely a programmer error.
     */
    INVALID_GENERATED_KEYIMAGE(29),

    /**
     * Could not contact the daemon to complete the request. Ensure it is
     * online and not frozen.
     */
    DAEMON_OFFLINE(30),

    /**
     * An error occured whilst the daemon processed the request. Possibly our
     * software is outdated, the daemon is faulty, or there is a programmer
     * error. Check your daemon logs for more info (set_log 4).
     */
    DAEMON_ERROR(31),

    /**
     * The transction is too large (in BYTES, not AMOUNT) to fit in a block.
     * Either:
     *     1) decrease the amount you are sending
     *     2) decrease the mixin value
     *     3) split your transaction up into multiple smaller transactions
     *     4) perform fusion transaction to combine multiple small inputs into fewer, larger inputs.
     */
    TOO_MANY_INPUTS_TO_FIT_IN_BLOCK(32),

    /**
     * Mnemonic has a word that is not in the english word list.
     */
    MNEMONIC_INVALID_WORD(33),

    /**
     * Mnemonic seed is not 25 words.
     */
    MNEMONIC_WRONG_LENGTH(34),

    /**
     * The mnemonic seed has an invalid checksum word.
     */
    MNEMONIC_INVALID_CHECKSUM(35),

    /**
     * Don't have enough inputs to make a fusion transaction, wallet is fully optimized.
     */
    FULLY_OPTIMIZED(36),

    /**
     * Mixin given for this fusion transaction is too large to be able to hit
     * the min input requirement.
     */
    FUSION_MIXIN_TOO_LARGE(37),

    /**
     * Attempted to add a subwallet which already exists in the container.
     */
    SUBWALLET_ALREADY_EXISTS(38),

    /**
     * Cannot perform this operation when using a view wallet.
     */
    ILLEGAL_VIEW_WALLET_OPERATION(39),

    /**
     * Cannot perform this operation when using a non view wallet.
     */
    ILLEGAL_NON_VIEW_WALLET_OPERATION(40),

    /**
     * View key is not derived from spend key for this address.
     */
    KEYS_NOT_DETERMINISTIC(41),

    /**
     * The primary address cannot be deleted.
     */
    CANNOT_DELETE_PRIMARY_ADDRESS(42),

    /**
     * Couldn't find the private key for this hash.
     */
    TX_PRIVATE_KEY_NOT_FOUND(43),

    /**
     * Amounts not a member of PRETTY_AMOUNTS.
     */
    AMOUNTS_NOT_PRETTY(44),

    /**
     * Tx fee is not the same as specified fee.
     */
    UNEXPECTED_FEE(45),

    /**
     * Value given is negative, but must be >= 0.
     */
    NEGATIVE_VALUE_GIVEN(46),

    /**
     * Key is not 64 char hex.
     */
    INVALID_KEY_FORMAT(47),

    /**
     * Hash not 64 chars.
     */
    HASH_WRONG_LENGTH(48),

    /**
     * Hash not hex.
     */
    HASH_INVALID(49),

    /**
     * Number is a float, not an integer.
     */
    NON_INTEGER_GIVEN(50),

    /**
     * Not on ed25519 curve.
     */
    INVALID_PUBLIC_KEY(51),

    /**
     * Not on ed25519 curve.
     */
    INVALID_PRIVATE_KEY(52),

    /**
     * Extra data for transaction is not a valid hexadecimal string.
     */
    INVALID_EXTRA_DATA(53),

    /**
     * An unknown error occured.
     */
    UNKNOWN_ERROR(54),

    /**
     * The daemon received our request but we timed out before we could figure
     * out if it completed.
     */
    DAEMON_STILL_PROCESSING(55),

    /**
     * The transaction has more outputs than are permitted for the number
     * inputs that have been provided
     */
    OUTPUT_DECOMPOSITION(56),

    /**
     * The inputs that were included in a prepared transaction have since been
     * spent or are for some other reason no longer available.
     */
    PREPARED_TRANSACTION_EXPIRED(57),

    /**
     * The prepared transaction hash specified does not exist, either because
     * it never existed, or because the wallet was restarted and the prepared
     * transaction state was lost.
     */
    PREPARED_TRANSACTION_NOT_FOUND(58),

    /**
     * The amount given does not have only a single significant digit - i.e.,
     * it cannot be used directly as a transaction input/output amount.
     */
    AMOUNT_UGLY(59),

    /**
     * The API requests a body.
     */
    API_BODY_REQUIRED(60),

    /**
     * The API does not have the block explorer enabled.
     */
    API_BLOCKEXPLORER_DISABLED(61),

    /**
     * The daemon must be synced to use this method.
     */
    API_NODE_NOT_SYNCED(62),

    /**
     * An argument supplied to the API endpoint is invalid.
     */
    API_INVALID_ARGUMENT(63),

    /**
     * An internal API error occurred.
     */
    API_INTERNAL_ERROR(64),

    /**
     * Could not add transaction to the transaction pool via API.
     */
    API_TRANSACTION_POOL_INSERT_FAILED(65),

    /**
     * Could not add candidate block to blockchain via API.
     */
    API_BLOCK_NOT_ACCEPTED(66),

    /**
     * Could not find the requested item.
     */
    API_HASH_NOT_FOUND(67),

    /**
     * A Ledger transport is required.
     */
    LEDGER_TRANSPORT_REQUIRED(68),

    /**
     * Ledger based wallets do not currently support subwallets.
     */
    LEDGER_SUBWALLETS_NOT_SUPPORTED(69),

    /**
     * Could not retrieve wallet keys from Ledger device.
     */
    LEDGER_COULD_NOT_GET_KEYS(70),

    /**
     * Incorrect Ledger wallet connected for this wallet file.
     */
    LEDGER_WRONG_DEVICE_FOR_WALLET_FILE(71);

    private int code;

    WalletErrorCode(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }

}
