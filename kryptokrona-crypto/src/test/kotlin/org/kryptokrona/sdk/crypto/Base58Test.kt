package org.kryptokrona.sdk.crypto

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests for Base58 class.
 *
 * @author Marcus Cvjeticanin
 * @since 0.2.0
 */
class Base58Test {

    @Test
    fun `encode should return an empty string when input is empty`() {
        val input = ByteArray(0)
        val expectedResult = ""

        val result = Base58.encode(input)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `encode should return string with only the encoded zero character when input contains only zeros`() {
        val input = ByteArray(5) { 0 }
        val expectedResult = "11111"

        val result = Base58.encode(input)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `encode should return string with leading encoded zeros when input contains leading zeros`() {
        val input = byteArrayOf(0, 0, 0, 0, 0x01, 0x02, 0x03)
        val expectedResult = "1111Ldp"

        val result = Base58.encode(input)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `encode should return string without leading encoded zeros when input contains no leading zeros`() {
        val input = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05)
        val expectedResult = "7bWpTW"

        val result = Base58.encode(input)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `encode should return correct string when input contains alternating zeros and ones`() {
        val input = byteArrayOf(0, 1, 0, 1, 0, 1, 0, 1)
        val expectedResult = "13CUz2N5n8"

        val result = Base58.encode(input)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `encode should return correct string when input contains all possible byte values in reverse order`() {
        val input = ByteArray(256) { (255 - it).toByte() }
        val expectedResult = "CqFXikQpYU36v9H3hxQj61r38coek8SwL1mCGWxTx4UxQPC4Cb7hcgpU75QJHoeBRW8jwW2wFv7t2P7" +
                "GiSkk2XJ8pWR6bJ9wrKSktCa16yURhMSS1Vew8hh7qobLPWZNSzrZ6zBRpLzwJLdrKcWsc6rpDpMQMCGHfiKPesYWzyFU" +
                "goUf1eNmkxRYxAwZqGmgsjAy98wzVEVAn5gLA2gnWqVVWxLbwVRFsnXr8kQbGriMjJN51QqM2KbdN1idx8wHEZyyBCFHH5" +
                "MEjr3qSssjPmhpfBN5m21gaMJP2PRqkVMKJWGwZYHkGaa9DEQFqi5XRvBQhr22FY3ZBtjaoq6FUMZAiFeerj"

        val result = Base58.encode(input)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `decode should return empty byte array when input is empty`() {
        val input = ""
        val expectedResult = ByteArray(0)

        val result = Base58.decode(input)

        assertArrayEquals(expectedResult, result)
    }

    @Test
    fun `decode should return byte array with only zeros when input contains only encoded zero characters`() {
        val input = "11111"
        val expectedResult = ByteArray(5) { 0 }

        val result = Base58.decode(input)

        assertArrayEquals(expectedResult, result)
    }

    @Test
    fun `decode should return byte array without leading zeros when input contains no leading encoded zeros`() {
        val input = "7bWpTW"
        val expectedResult = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05)

        val result = Base58.decode(input)

        assertArrayEquals(expectedResult, result)
    }

    @Test
    fun `decode should return correct byte array when input contains all possible encoded characters`() {
        val input = "7bWpTW"
        val expectedResult = byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05)

        val result = Base58.decode(input)

        assertArrayEquals(expectedResult, result)
    }

    @Test
    fun `decode should throw IllegalArgumentException when input contains invalid encoded characters`() {
        val input = "7bWpTW!@#"

        assertThrows<IllegalArgumentException> {
            Base58.decode(input)
        }
    }

    @Test
    fun `decodeToBigInteger should return 0 for empty input`() {
        val input = ""
        val expected = BigInteger.ZERO

        val result = Base58.decodeToBigInteger(input)

        assertEquals(expected, result)
    }

    @Test
    fun `decodeToBigInteger should return 58 for input 21`() {
        val input = "21"
        val expected = BigInteger.valueOf(58)

        val result = Base58.decodeToBigInteger(input)

        assertEquals(expected, result)
    }

    @Test
    fun `decodeToBigInteger should return 31234063 for input 3m5oL`() {
        val input = "3m5oL"
        val expected = BigInteger.valueOf(31234063)

        val result = Base58.decodeToBigInteger(input)

        assertEquals(expected, result)
    }

    @Test
    fun `decodeToBigInteger should return 1392151476001123057 for input 4ERpubtTJ7r`() {
        val input = "4ERpubtTJ7r"
        val expected = BigInteger.valueOf(1392151476001123057)

        val result = Base58.decodeToBigInteger(input)

        assertEquals(expected, result)
    }

    @Test
    fun `decodeToBigInteger should return X for input Y`() {
        val input = "1QLbz7JHiBTspS962RLKV8GndWFwi5j6Qr"
        val expected = BigInteger("6277101735386680763835789423207666416102355444463934276107")

        val result = Base58.decodeToBigInteger(input)

        assertEquals(expected, result)
    }
}
