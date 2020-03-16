package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/27/19.
 * <p>
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1:
 * Input: 123
 * Output: 321
 * <p>
 * Example 2:
 * Input: -123
 * Output: -321
 * </p>
 */
public class _7_ReverseInteger {
    // NOTE: see the 2nd and 3rd solutions: no integer to string conversions there
    private static int reverse(int inputNumber) {
        int reverseInteger = 0;

        if (inputNumber <= Integer.MIN_VALUE || inputNumber >= Integer.MAX_VALUE) {
            return reverseInteger;
        }

        String inputNumberString = String.valueOf(Math.abs(inputNumber));

        for (int i = inputNumberString.length() - 1; i >= 0; i--) {
            reverseInteger += (inputNumberString.charAt(i) - '0') * Math.pow(10, i);
        }

        if (reverseInteger >= Math.pow(2, 31) - 1) {
            return 0;
        }
        return inputNumber < 0 ? -reverseInteger : reverseInteger;
    }

    private static int reverse2(int inputNumber) {
        int reverseInteger = 0;
        boolean isNegative = false;

        if (inputNumber <= Integer.MIN_VALUE || inputNumber >= Integer.MAX_VALUE) {
            return reverseInteger;
        }
        if (inputNumber <= 0) {
            isNegative = true;
        }

        int inputIntegerAbs = Math.abs(inputNumber);
        while (inputIntegerAbs != 0) {

            // NOTE: to take care of overflow case whenever it happens
            if (reverseInteger > (Integer.MAX_VALUE - (inputIntegerAbs % 10)) / 10) {
                return 0;
            }

            reverseInteger = (reverseInteger * 10) + (inputIntegerAbs % 10);
            inputIntegerAbs = inputIntegerAbs / 10;
        }
        return isNegative ? -reverseInteger : reverseInteger;
    }

    // NOTE: except that we are using long and type casting, this is simplest and more optimal
    private static int reverse3(int inputNumber) {
        long reverseInteger = 0;

        while (inputNumber != 0) {
            reverseInteger = (reverseInteger * 10) + (inputNumber % 10);
            inputNumber = inputNumber / 10;

            // whenever overflow occurs, return 0
            if (reverseInteger > Integer.MAX_VALUE || reverseInteger < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) reverseInteger;
    }

    public static void main(String[] args) {
        assertEquals(reverse(123), 321);
        assertEquals(reverse(-123), -321);
        assertEquals(reverse(120), 21);
        assertEquals(reverse(1534236469), 0);
        assertEquals(reverse(-2147483648), 0);

        assertEquals(reverse2(123), 321);
        assertEquals(reverse2(-123), -321);
        assertEquals(reverse2(120), 21);
        assertEquals(reverse2(1534236469), 0);
        assertEquals(reverse2(-2147483648), 0);

        assertEquals(reverse3(123), 321);
        assertEquals(reverse3(-123), -321);
        assertEquals(reverse3(120), 21);
        assertEquals(reverse3(1534236469), 0);
        assertEquals(reverse3(-2147483648), 0);
    }
}
