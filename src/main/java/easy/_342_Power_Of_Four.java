package easy;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 2/10/20.
 * <p>
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * </p>
 */
public class _342_Power_Of_Four {
    // simple iteration
    private static boolean isPowerOfFour(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    // simple recursion
    private static boolean isPowerOfFour2(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 1) {
            return false;
        }
        return (n % 4 == 0) && isPowerOfFour2(n / 4);
    }

    public static void main(String[] args) {
        // method 1
        assertTrue(isPowerOfFour(4));
        assertTrue(isPowerOfFour(15));
        assertFalse(isPowerOfFour(5));

        // method 2
        assertTrue(isPowerOfFour2(4));
        assertTrue(isPowerOfFour2(15));
        assertFalse(isPowerOfFour2(5));
    }
}