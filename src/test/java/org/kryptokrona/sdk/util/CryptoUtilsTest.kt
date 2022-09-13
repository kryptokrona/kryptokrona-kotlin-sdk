package org.kryptokrona.sdk.util;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CryptoUtilsTest {

    @Test
    fun `can enter is valid mnemonic word`() {
        CryptoUtils.isValidMnemonicWord("adapt")
    }
}