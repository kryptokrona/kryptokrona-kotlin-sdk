package org.kryptokrona.sdk.util;

import org.junit.jupiter.api.Test
import org.kryptokrona.sdk.exception.wallet.WalletMnemonicInvalidWordException
import org.kryptokrona.sdk.exception.wallet.WalletMnemonicWrongLengthException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CryptoUtilsTest {

    private var validMnemonicWords = listOf(
        "abducts", "ability", "ablaze", "abnormal", "abort",
        "abrasive", "absorb", "abyss", "academy", "aces",
        "aching", "acidic", "acoustic", "acquire", "across",
        "actress", "acumen", "adapt", "addicted", "adept",
        "adhesive", "adjust", "adopt", "adrenalin", "adult"
    )

    private var invalidMnemonicWords = listOf(
        "mjovanc", "kryptoknugen", "nilsjr", "coffeboi", "techyguy",
        "swepool", "bingo1168", "czmsl", "zyf2021", "hoover",
        "exbitron", "freiexchange", "hugin", "munin", "svelte",
        "kryptokrona", "xkr", "java", "rxjava", "gradle",
        "kotlin", "spring", "sdk", "daemon", "pool"
    )

    private var invalidMnemonicWordsLength = listOf(
        "abducts", "ability", "ablaze", "abnormal", "abort",
        "abrasive", "absorb", "abyss", "academy", "aces",
        "aching", "acidic", "acoustic", "acquire", "across",
        "actress", "acumen", "adapt", "addicted", "adept",
        "adhesive", "adjust", "adopt", "adrenalin", "adult",
        "afar", "affair", "afield",
    )

    @Test
    fun `can enter valid mnemonic word`() {
        assertTrue(CryptoUtils.isValidMnemonicWord("adapt"))
    }

    @Test
    fun `can not enter invalid mnemonic word`() {
        assertFalse(CryptoUtils.isValidMnemonicWord("mjovanc"))
    }

    @Test
    fun `can enter valid mnemonic words`() {
        CryptoUtils.isValidMnemonic(validMnemonicWords)
            .subscribe { validity ->
                assertTrue(validity)
            }
    }

    @Test
    fun `can not enter mnemonic words with invalid words`() {
        assertFailsWith<WalletMnemonicInvalidWordException> {
            CryptoUtils.isValidMnemonic(invalidMnemonicWords)
                .subscribe { }
        }
    }

    @Test
    fun `can not enter valid mnemonic words with lower or higher than 25 words`() {
        assertFailsWith<WalletMnemonicWrongLengthException> {
            CryptoUtils.isValidMnemonic(invalidMnemonicWordsLength)
                .subscribe { }
        }
    }

    @Test
    fun `input is unlocked`() {
        assertTrue(CryptoUtils.isInputUnlocked(0, 1000))
    }

    @Test
    fun `input is locked`() {
        assertTrue(CryptoUtils.isInputUnlocked(100, 1000))
    }

    @Test
    fun `can pretty print an amount`() {
        val prettyPrintedAmount1 = CryptoUtils.prettyPrintAmount(12345.01)
        val prettyPrintedAmount2 = CryptoUtils.prettyPrintAmount(123456.01)
        val prettyPrintedAmount3 = CryptoUtils.prettyPrintAmount(1234567.01)
        val prettyPrintedAmount4 = CryptoUtils.prettyPrintAmount(12345678.01)
        val prettyPrintedAmount5 = CryptoUtils.prettyPrintAmount(123456789.01)

        assertEquals("XKR 12,345.01", prettyPrintedAmount1)
        assertEquals("XKR 123,456.01", prettyPrintedAmount2)
        assertEquals("XKR 1,234,567.01", prettyPrintedAmount3)
        assertEquals("XKR 12,345,678.01", prettyPrintedAmount4)
        assertEquals("XKR 123,456,789.01", prettyPrintedAmount5)
    }
}