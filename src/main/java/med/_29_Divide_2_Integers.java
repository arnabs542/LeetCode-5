package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/24/19.
 * <p>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * </p>
 */
public class _29_Divide_2_Integers {
    // NOTE: time limit exceeded. very bad solution. see the below recursive approach which is good
    private static int divide(int dividend, int divisor) {
        boolean isSignNegative = false;
        if ((divisor < 0 && dividend > 0) || (divisor > 0 && dividend < 0)) {
            isSignNegative = true;
        }

        /*if (dividend < divisor) {
            return 0;
        }*/

        long result = 0;
        long absDivisor = Math.abs((long) divisor);
        long absDividend = Math.abs((long) dividend);
        long sum = absDivisor;

        /*while (sum + sum <= absDividend) {
            sum += sum;
            result += result;
        }*/

        while (sum <= absDividend) {
            sum = sum + absDivisor;
            result++;
        }
        return isSignNegative ? -(int) result : result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
    }

    // recursive approach
    private static int divideRecursive(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) {
            return 0;
        }

        // convert everything to long to avoid stack overflows / corner cases
        long longDivisor = (long) divisor;
        long longDividend = (long) dividend;
        long result = helper(Math.abs(longDividend), Math.abs(longDivisor));
        result = longDivisor * longDividend < 0 ? -result : result;
        return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) result;
    }

    private static long helper(long dividend, long divisor) {
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor, quotient = 1;
        while (sum + sum <= dividend) {
            sum += sum;
            quotient += quotient;
        }
        return quotient + helper(dividend - sum, divisor);
    }

    public static void main(String[] args) {
        // test iterative approach
        assertEquals(divide(10, 3), 3);
        assertEquals(divide(7, -3), -2);
         assertEquals(divide(-2147483648, -1), 2147483647);

        // test recursive approach
        assertEquals(divideRecursive(10, 3), 3);
        assertEquals(divideRecursive(7, -3), -2);
        assertEquals(divideRecursive(-2147483648, -1), 2147483647);
    }
}