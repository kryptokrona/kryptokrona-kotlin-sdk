package org.kryptokrona.sdk.crypto.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Tests for the HexUtil class.
 *
 * These tests are exhaustive and should cover all possible cases.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class HexUtilTest {

    @Test
    fun `test empty byte array`() {
        val bytes = byteArrayOf()
        val hex = toHex(bytes)
        assertEquals("", hex)
    }

    @Test
    fun `test single byte`() {
        val bytes = byteArrayOf(0x01)
        val hex = toHex(bytes)
        assertEquals("01", hex)
    }

    @Test
    fun `test multiple bytes`() {
        val bytes = byteArrayOf(0x0f, 0x00, 0x7f, 0x80.toByte(), 0xff.toByte())
        val hex = toHex(bytes)
        assertEquals("0f007f80ff", hex)
    }

    @Test
    fun `test max value byte`() {
        val bytes = byteArrayOf(Byte.MAX_VALUE)
        val hex = toHex(bytes)
        assertEquals("7f", hex)
    }

    @Test
    fun `test min value byte`() {
        val bytes = byteArrayOf(Byte.MIN_VALUE)
        val hex = toHex(bytes)
        assertEquals("80", hex)
    }

    @Test
    fun `test random bytes`() {
        val bytes = byteArrayOf(0x23, 0xf4.toByte(), 0x89.toByte(), 0x12, 0x6a, 0x8c.toByte())
        val hex = toHex(bytes)
        assertEquals("23f489126a8c", hex)
    }

    @Test
    fun `test empty string`() {
        val input = ""
        val expected = byteArrayOf()
        val actual = fromHex(input)
        assertEquals(expected.contentToString(), actual.contentToString())
    }

    @Test
    fun `test even number of characters`() {
        val input = "48656c6c6f20576f726c64" // "Hello World" in hex
        val expected = "Hello World".toByteArray()
        val actual = fromHex(input)
        assertTrue(expected.contentEquals(actual))
    }

    @Test
    fun `test invalid characters`() {
        val input = "zzzzzzzzzzzzzzzzzzzz"
        assertFailsWith<IllegalArgumentException> {
            fromHex(input)
        }
    }

    @Test
    fun `test mixed case`() {
        val input = "48656C6C6F20576F726C64"
        val expected = "Hello World".toByteArray()
        val actual = fromHex(input)
        assertTrue(expected.contentEquals(actual))
    }
}
