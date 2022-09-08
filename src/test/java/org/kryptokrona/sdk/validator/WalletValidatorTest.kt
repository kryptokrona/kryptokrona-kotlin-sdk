package org.kryptokrona.sdk.validator;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.exception.wallet.WalletAddressNotBase58Exception
import org.kryptokrona.sdk.exception.wallet.WalletAddressWrongLengthException
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WalletValidatorTest {

    private lateinit var walletValidator: WalletValidator

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

    @BeforeEach
    fun setup() {
        walletValidator = WalletValidator()
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
}
