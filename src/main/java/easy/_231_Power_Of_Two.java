package easy;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 2/10/20.
 * <p>
 * Given an integer, write a function to determine if it is a power of two.
 * </p>
 */
public class _231_Power_Of_Two {
    // simple iteration
    private static boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    // simple recursion
    private static boolean isPowerOfTwo2(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 1) {
            return false;
        }
        return (n % 2 == 0) && isPowerOfTwo2(n / 2);
    }

    // 1073741824 is the max possible power of 2 in the integer range. so any power of 2 should be exactly divided by this number
    private static boolean isPowerOfTwo3(int n) {
        return n > 0 && (1073741824 % n == 0);
    }

    public static void main(String[] args) {
        // method 1
        assertTrue(isPowerOfTwo(1));
        assertTrue(isPowerOfTwo(16));
        assertFalse(isPowerOfTwo(218));

        // method 2
        assertTrue(isPowerOfTwo2(1));
        assertTrue(isPowerOfTwo2(16));
        assertFalse(isPowerOfTwo2(218));
    }
}