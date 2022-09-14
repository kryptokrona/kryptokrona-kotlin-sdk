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
}