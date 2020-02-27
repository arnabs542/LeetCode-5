package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/28/19.
 * <p>
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 * Example 1:
 * Input: "42"
 * Output: 42
 * <p>
 * Example 2:
 * Input: "4193 with words"
 * Output: 4193
 * </p>
 */
public class _8_StringToInteger {
    private static int myAtoi(String str) {
        long result = 0;
        boolean isNegative = false;
        boolean isPositive = false;
        int i = 0;
        String trimmedString = str.trim();

        // return zero: if string is empty or the first character is other than {+, -, digit}
        if (trimmedString.length() == 0 || !(Character.isDigit(trimmedString.charAt(0)) || (trimmedString.charAt(0)) == '-' || trimmedString.charAt(0) == '+')) {
            return 0;
        }

        if (trimmedString.charAt(0) == '-') {
            isNegative = true;
        }
        if (trimmedString.charAt(0) == '+') {
            isPositive = true;
        }

        // get the valid end index of the string (valid string is the one which only contains {+, -, digits})
        while (i < trimmedString.length()) {
            if (i == 0) {
                if (Character.isDigit(trimmedString.charAt(i)) || isNegative || isPositive) {
                    i++;
                }
            } else {
                if (Character.isDigit(trimmedString.charAt(i))) {
                    i++;
                } else {
                    break;
                }
            }
        }

        // remove the sign (if present) of the string and calculate the numeric value of the string
        trimmedString = (isNegative || isPositive) ? trimmedString.substring(1, i) : trimmedString.substring(0, i);
        for (int j = trimmedString.length() - 1; j >= 0; j--) {
            result += (trimmedString.charAt(trimmedString.length() - j - 1) - '0') * Math.pow(10, j);
        }

        // append the sign as needed in the result and return the appropriate value
        result = isNegative ? -result : result;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }

    public static void main(String[] args) {
        assertEquals(myAtoi("42"), 42);
        assertEquals(myAtoi("-42"), -42);
        assertEquals(myAtoi("words and 987"), 0);
        assertEquals(myAtoi("-91283472332"), -2147483648);
        assertEquals(myAtoi(" "), 0);
        assertEquals(myAtoi("  "), 0);
        assertEquals(myAtoi("+1"), 1);
        assertEquals(myAtoi("+-2"), 0);
        assertEquals(myAtoi("234s2"), 234);
    }
}