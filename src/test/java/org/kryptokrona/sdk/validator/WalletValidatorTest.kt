package org.kryptokrona.sdk.validator;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.exception.wallet.*
import org.kryptokrona.sdk.model.util.FeeType
import org.kryptokrona.sdk.wallet.SubWallets
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WalletValidatorTest {

    private lateinit var walletValidator: WalletValidator

    private var correctAddress = "SEKReUGeSDdZA6Fq6EHrKENoKpYtzEjuyYNXF48Lps2qYhjYw7dBAZ7VKeSMs6zjy64e2tWGB1RCi9nSHVKgHgBUcvNgcd9maiu"

    private var correctAddresses = listOf(
        "SEKReUGeSDdZA6Fq6EHrKENoKpYtzEjuyYNXF48Lps2qYhjYw7dBAZ7VKeSMs6zjy64e2tWGB1RCi9nSHVKgHgBUcvNgcd9maiu",
        "SEKReWjf8bYKDhnLcuLRqEBVMd2uXsV288rMkoNkmUJa3K8QhBiCbX7LeKs5qj3wqbNYm13ErCSRwbee7b8sbNmdj4sRYEkbhXF",
        "SEKReSh3MZXUWCNGJWe2h88AaFGu8ZRNvHj8wAbdQeNA42CsBdTQ56VFJnDodXXDV9Nt2E7NygTiLRJLVQ1SJbQkMjvS33gQjUN",
        "SEKReZ7NNH7MfEpgoPbD4pCziHmNDuBggfYFTiepT1T3KzJ8era7qC2jSywQrSYjzqiyaaXa85Qjy8QkNW8K7rksiJv8GitQ9Zy",
        "SEKReWD8q3EKsT1yD7jTrj5tpDPS64Xwd5h9TSYRkLFx2GBuDFtBhTKRHAYr7a9yJnZ9Dgr9qJZ7VGwwTDkKDoUhUF7Krhf9pgb"
    )

    private var incorrectAddressesWithWrongLength = listOf(
        "KReUGeSDdZA6Fq6EHrKENoKpYtzEjuyYNXF48Lps2qYw7dBAZ7VKeSMs6zjy64e2tWGB1RCi9nSHVKgHgBUcvNgcd9maiu",
        "EKReWjf8bYKDhnLcuLRqEBVMdhBiCbX7LeKs5qj3wqbNYm13ErCSRwbee7b8sbNmdj4sRYEkbhXF",
        "SEKReSh3MZXUWCNGJWe2h88AaFGu8ZRNvHj8wAbdQeNA42CsBdTQ56VFJ-nDodXXDV9Nt2E7NygTiLR1SJbQkMjvS33gQjUN",
        "SEKReZ7NNH7MfEpgoPbD4pCziHmNDuBggfYFTiepT1T3KzJ8era7qC2jSy/wQrSYjzqiyaaXa85Qjy8QkNW8K7rksiJv8GitQ9Zy",
        "SEKReWD8q3EKsT1yD7jTrj5tpDPS64Xwd5h9TSYRkLFx2GBuDFtBhTKRHAYr7a9yJnZ9Dgr9qJZ7VGwwT+KDoUhUF7Krhf9pgb"
    )

    private var incorrectAddressesNotBase58 = listOf(
        "SEKReUGeSDdZA6Fq6EHrKENoKpYtzEjuyYNXF48Lps2qYhjYw7dBAZ7VKeSMs6zjy64e2tWGB1RCi9nSHVKgHgBUcvNgcd9mai+",
        "SEKReWjf8bYKDhnLcuLRqEBVMd2uXsV288rMkoNkmUJa3K8QhBiCbX7LeKs5qj3wqbNYm13ErCSRwbee7b8sbNmdj4sRYEkbhX-",
        "SEKReSh3MZXUWCNGJWe2h88AaFGu8ZRNvHj8wAbdQeNA42CsBdTQ56VFJnDodXXDV9Nt2E7NygTiLRJLVQ1SJbQkMjvS33gQjU/",
        "SEKReZ7NNH7MfEpgoPbD4pCziHmNDuBggfYFTiepT1T3KzJ8era7qC2jSywQrSYjzqiyaaXa85Qjy8QkNW8K7rksiJv8GitQ9Z|",
        "SEKReWD8q3EKsT1yD7jTrj5tpDPS64Xwd5h9TSYRkLFx2GBuDFtBhTKRHAYr7a9yJnZ9Dgr9qJZ7VGwwTDkKDoUhUF7Krhf9pg="
    )

    private var correctAmountWalletDestinations = mapOf(
        "SEKReTFuVRPQaiwzPBD1Gx4oyYUNSABLe3eQANxMEYKTbRRgYJNKf1yG5VJpbq4wuY67y2uWwMBcA9Xb9gft8XXoUU2DguV9yzj" to 10.0,
        "SEKReWU9LE8HLPRAGu2dyMF8jE93xabHv1Yf92YLtqZn7L3evAQ8Sbm3y8KnZf2LGPgP1bwqD9qua3z57q2qgBavjVKTmSeq4oK" to 5.0,
        "SEKReSywEaajP1hsreeveEhdY48Vu6vZn2uN6dy1tiH96d8gzHT4xzZfNx9xfuhK8kWjfouGYaLg8WXifDHPKMAufLt2dAwbmbH" to 2.0,
        "SEKReWtkaTbBWT645J5nz83SRzGhUui1qaa6AWYGUBNN9nRxQitMjtWUhSHLiKrkxBCkamZxrMNWvAGKRBBpnMS7bn9Gi5mPfu8" to 120.0,
        "SEKReV7v3hV9BnhNoYNJGkB3WiNvapTAFJEkc3PbDVpMMQrDABbTwi9gJtQyghxs66Xwpj8BDqX4KEis3M4SkRqx3Gfwf4DBEiV" to 500.0,
    )

    private var emptyWalletDestinations = emptyMap<String, Double>()

    private var amountWalletDestinationsIsZero = mapOf(
        "SEKReTFuVRPQaiwzPBD1Gx4oyYUNSABLe3eQANxMEYKTbRRgYJNKf1yG5VJpbq4wuY67y2uWwMBcA9Xb9gft8XXoUU2DguV9yzj" to 0.0,
        "SEKReWU9LE8HLPRAGu2dyMF8jE93xabHv1Yf92YLtqZn7L3evAQ8Sbm3y8KnZf2LGPgP1bwqD9qua3z57q2qgBavjVKTmSeq4oK" to 0.0,
        "SEKReSywEaajP1hsreeveEhdY48Vu6vZn2uN6dy1tiH96d8gzHT4xzZfNx9xfuhK8kWjfouGYaLg8WXifDHPKMAufLt2dAwbmbH" to 0.0,
        "SEKReWtkaTbBWT645J5nz83SRzGhUui1qaa6AWYGUBNN9nRxQitMjtWUhSHLiKrkxBCkamZxrMNWvAGKRBBpnMS7bn9Gi5mPfu8" to 0.0,
        "SEKReV7v3hV9BnhNoYNJGkB3WiNvapTAFJEkc3PbDVpMMQrDABbTwi9gJtQyghxs66Xwpj8BDqX4KEis3M4SkRqx3Gfwf4DBEiV" to 0.0,
    )

    private var amountWalletDestinationsIsNegative = mapOf(
        "SEKReTFuVRPQaiwzPBD1Gx4oyYUNSABLe3eQANxMEYKTbRRgYJNKf1yG5VJpbq4wuY67y2uWwMBcA9Xb9gft8XXoUU2DguV9yzj" to -4.0,
        "SEKReWU9LE8HLPRAGu2dyMF8jE93xabHv1Yf92YLtqZn7L3evAQ8Sbm3y8KnZf2LGPgP1bwqD9qua3z57q2qgBavjVKTmSeq4oK" to -2.0,
        "SEKReSywEaajP1hsreeveEhdY48Vu6vZn2uN6dy1tiH96d8gzHT4xzZfNx9xfuhK8kWjfouGYaLg8WXifDHPKMAufLt2dAwbmbH" to -10.0,
        "SEKReWtkaTbBWT645J5nz83SRzGhUui1qaa6AWYGUBNN9nRxQitMjtWUhSHLiKrkxBCkamZxrMNWvAGKRBBpnMS7bn9Gi5mPfu8" to 5.0,
        "SEKReV7v3hV9BnhNoYNJGkB3WiNvapTAFJEkc3PbDVpMMQrDABbTwi9gJtQyghxs66Xwpj8BDqX4KEis3M4SkRqx3Gfwf4DBEiV" to 2.0,
    )

    private val subWalletsToTakeFrom = listOf(
        "5fd3e4dc30f0ddaa0abff82d3c0b891216e7b908670dcb8c3fc3896a7a07cd06",
        "f185315f831f8411bc19f6bbf67689eaaab4a73453c01db3468f3a296bd584ef",
        "6e072c297356994934c5ca7ca80938e088794591a5ecc16dbdb4e8047cd47007",
        "f2e1a6ec23c143ff4a959a8ebe2ddb1e02ec0b2634a2fe1bb4c4b8571abcf3dc",
        "466d5db56c5dbf193f4643988a0e932fdb23996c2a89a81f5a2db4605ed262b0"
    )

    @BeforeEach
    fun setup() {
        walletValidator = WalletValidator()
    }

    @Test
    fun `can validate wallet addresse when integrated addresses are allowed`() {
        walletValidator.validateAddress(correctAddress, true)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can validate wallet addresse when integrated addresses are not allowed`() {
        walletValidator.validateAddress(correctAddress, false)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can validate wallet addresses when integrated addresses are allowed`() {
        walletValidator.validateAddresses(correctAddresses, true)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can validate wallet addresses when integrated addresses are not allowed`() {
        walletValidator.validateAddresses(correctAddresses, false)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can not validate incorrect wallet addresses with wrong length when integrated addresses are allowed`() {
        assertFailsWith<WalletAddressWrongLengthException> {
            walletValidator.validateAddresses(incorrectAddressesWithWrongLength, true)
                .subscribe { }
        }
    }

    @Test
    fun `can not validate incorrect base58 wallet addresses when integrated addresses are not allowed`() {
        assertFailsWith<WalletAddressNotBase58Exception> {
            walletValidator.validateAddresses(incorrectAddressesNotBase58, false)
                .subscribe { }
        }
    }

    @Test
    fun `can validate destinations`() {
            walletValidator.validateDestinations(correctAmountWalletDestinations)
                .subscribe { validity ->
                    assertTrue { validity }
                }
    }

    @Test
    fun `can not validate destinations when wallet destinations are empty`() {
        assertFailsWith<WalletNoDestinationGivenException> {
            walletValidator.validateDestinations(emptyWalletDestinations)
                .subscribe { }
        }
    }

    @Test
    fun `can not validate destinations when wallet amount is 0`() {
        assertFailsWith<WalletAmountIsZeroException> {
            walletValidator.validateDestinations(amountWalletDestinationsIsZero)
                .subscribe { }
        }
    }

    @Test
    fun `can not validate destinations when wallet amount is negative`() {
        assertFailsWith<WalletNegativeValueGivenException> {
            walletValidator.validateDestinations(amountWalletDestinationsIsNegative)
                .subscribe { }
        }
    }

    @Test
    fun `can validate integrated addresses`() {
        walletValidator.validateIntegratedAddresses(
            correctAmountWalletDestinations,
            "1eb80df3811e0d6d9ba937fc0fa040e823dbd13eec0a68c9833dc3fcf9ad2b03")
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can not validate integrated addresses when conflicting payment ids`() {
        //TODO: need to implement Address method fromAddress first before this will pass
        assertFailsWith<WalletConflictingPaymentIdsException> {
            walletValidator.validateIntegratedAddresses(
                correctAmountWalletDestinations,
                "1eb80df3811e0d6d9ba937fc0fa040e823dbd13eec0a68c9833dc3fcf9ad2b03")
                .subscribe { }
        }
    }

    @Test
    fun `can validate transfer amount and fee`() {
        val feeType = FeeType.minimumFee()

        val subWallets = SubWallets() //TODO: might need to initialize some values here and remove the @NoArgsConstructor decorator in class
        val currentHeight = 0L;

        walletValidator.validateAmount(correctAmountWalletDestinations, feeType, subWalletsToTakeFrom, subWallets, currentHeight)
            .subscribe { }
    }

    @Test
    fun `can not validate transfer amount and fee when wallet amount is negative`() {
        val feeType = FeeType()
        feeType.isFeePerByte = false
        feeType.isFixedFee = false

        val subWallets = SubWallets() //TODO: might need to initialize some values here and remove the @NoArgsConstructor decorator in class
        val currentHeight = 0L;

        assertFailsWith<WalletFeeTooSmallException> {
            walletValidator.validateAmount(correctAmountWalletDestinations, feeType, subWalletsToTakeFrom, subWallets, currentHeight)
                .subscribe { }
        }
    }

    @Test
    fun `can validate mixin if not more or less than min or max mixin`() {
        walletValidator.validateMixin(1, 900000)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can not validate mixin if negative value`() {
        assertFailsWith<WalletNegativeValueGivenException> {
            walletValidator.validateMixin(-10, 900000)
                .subscribe { }
        }
    }

    @Test
    fun `can not validate mixin if less than min mixin value`() {
        assertFailsWith<WalletMixinTooSmallException> {
            walletValidator.validateMixin(0, 900000)
                .subscribe { }
        }
    }

    @Test
    fun `can not validate mixin if more than max mixin value`() {
        assertFailsWith<WalletMixinTooBigException> {
            walletValidator.validateMixin(101, 900000)
                .subscribe { }
        }
    }

    @Test
    fun `can validate empty payment ID when empty string are allowed`() {
        walletValidator.validatePaymentID("", true)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can not validate empty payment ID when empty string are not allowed`() {
        assertFailsWith<WalletPaymentIdWrongLengthException> {
            walletValidator.validatePaymentID("", false)
                .subscribe { }
        }
    }

    @Test
    fun `can validate 64 char length payment ID when empty string are allowed`() {
        walletValidator.validatePaymentID(
            "ecd4621085009655b82f0eb19c3ccd0e4cc882bb87497c97d15b23b0fdabd20a",
            true)
            .subscribe { validity ->
                assertTrue { validity }
            }
    }

    @Test
    fun `can not validate payment ID with more or less than 64 char length when empty string are not allowed`() {
        assertFailsWith<WalletPaymentIdWrongLengthException> {
            walletValidator.validatePaymentID("ecd4621085009655b82f0eb19c3c", false)
                .subscribe { }
        }

        assertFailsWith<WalletPaymentIdWrongLengthException> {
            walletValidator.validatePaymentID(
                "ecd4621085009655b82f0eb19c3ccd0e4cc882bb87497c97d15b23b0fdabd20adasdasd2d12d",
                false)
                .subscribe { }
        }
    }

    @Test
    fun `can not validate payment ID with specical chars when empty string are not allowed`() {
        assertFailsWith<WalletPaymentIdInvalidException> {
            walletValidator.validatePaymentID(
                "ecd4621+8500#655b82f0eb19c3ccd0e4cc882!b87497c97d15b23b0fdabd20a",
                false)
                .subscribe { }
        }
    }
}
