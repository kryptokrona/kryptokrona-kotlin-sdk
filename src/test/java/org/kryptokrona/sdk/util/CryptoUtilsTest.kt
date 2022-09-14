package org.kryptokrona.sdk.util;

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CryptoUtilsTest {

    @Test
    fun `can enter valid mnemonic word`() {
        assertTrue(CryptoUtils.isValidMnemonicWord("adapt"));
    }

    @Test
    fun `can not enter invalid mnemonic word`() {
        assertFalse(CryptoUtils.isValidMnemonicWord("mjovanc"));
    }

    @Test
    fun `input is unlocked`() {
        assertTrue(CryptoUtils.isInputUnlocked(0, 1000));
    }

    @Test
    fun `input is locked`() {
        assertTrue(CryptoUtils.isInputUnlocked(100, 1000));
    }
}