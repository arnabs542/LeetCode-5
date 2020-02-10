package easy;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 2/9/20.
 * <p>
 * Given an integer, write a function to determine if it is a power of three.
 * </p>
 */
public class _326_Power_Of_Three {
    // simple iteration
    private static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // simple recursion
    private static boolean isPowerOfThree2(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 1) {
            return false;
        }
        return (n % 3 == 0) && isPowerOfThree2(n / 3);
    }

    // efficient solution: 3^19 = 1162261467 < Integer.Max_Value < 3^20
    private static boolean isPowerOfThree3(int n) {
        return (n > 0 && 1162261467 % n == 0);  // 1162261467 is the greater power of 3 in the integers, so any power of 3 should be exactly divided by that number
    }

    public static void main(String[] args) {
        // method: 1
        assertTrue(isPowerOfThree(27));
        assertTrue(isPowerOfThree(9));
        assertFalse(isPowerOfThree(0));
        assertFalse(isPowerOfThree(45));

        // method: 2
        assertTrue(isPowerOfThree2(27));
        assertTrue(isPowerOfThree2(9));
        assertFalse(isPowerOfThree2(0));
        assertFalse(isPowerOfThree2(45));

        // method: 3
        assertTrue(isPowerOfThree3(27));
        assertTrue(isPowerOfThree3(9));
        assertFalse(isPowerOfThree3(0));
        assertFalse(isPowerOfThree3(45));
    }
}