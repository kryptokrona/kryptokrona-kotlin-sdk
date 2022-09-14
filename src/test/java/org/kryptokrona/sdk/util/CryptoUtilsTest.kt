package org.kryptokrona.sdk.util;

import org.junit.jupiter.api.Test

class CryptoUtilsTest {

    @Test
    fun `can enter valid mnemonic word`() {
        CryptoUtils.isValidMnemonicWord("adapt")
    }
}