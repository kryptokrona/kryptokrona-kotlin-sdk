package org.kryptokrona.sdk.util;

/**
 * HexUtil.java
 *
 * @author Marcus Cvjeticanin (@mjovanc)
 */
public class HexUtil {

	public static boolean isHex(String value) {
		if (value.length() % 2 != 0) {
			return false;
		}

		String expression = String.format("^[0-9a-fA-F]{" + "%s" + "}$", value.length());

		return value.matches(expression);
	}

	public static boolean isHex64(String value) {
		return (isHex(value) && value.length() == 64);
	}

	public static boolean isHex128(String value) {
		return (isHex(value) && value.length() == 128);
	}
}
