package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 6/9/19.
 * <p>
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * </p>
 */
public class _263_UglyNumber {
    // simple iterative solution: for every divisor, keep dividing till its complete (remainder = 0). return true, if num == 1, after all the divisors are complete (which means the prime factors for the given number are only either 2, 3 or 5)
    // TC: O(logn)
    private static boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        int[] divisors = new int[]{2, 3, 5};

        for (int divisor : divisors) {
            while (num % divisor == 0) {
                num = num / divisor;
            }
        }
        return num == 1;
    }

    // simple recursive solution: same as above approach
    private static boolean isUglyRecursive(int num) {
        if (num <= 0) {
            return false;
        }

        if (num == 1) {
            return true;
        }

        if (num % 2 == 0) {
            return isUglyRecursive(num / 2);
        }

        if (num % 3 == 0) {
            return isUglyRecursive(num / 3);
        }

        if (num % 5 == 0) {
            return isUglyRecursive(num / 5);
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(isUgly(6));
        assertTrue(isUgly(8));
        assertFalse(isUgly(14));

        assertTrue(isUglyRecursive(6));
        assertTrue(isUglyRecursive(8));
        assertFalse(isUglyRecursive(14));
    }
}