package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 2/1/20.
 * <p>
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * </p>
 */
public class _367_Valid_Perfect_Square {
    // core logic: the fact that the square root of the number always lies in between 0 and n/2, we can use the binary search to find the right number
    // set the low to 1 and high to n/2 and proceed with the binary search
    // used longs for low, mid and high to avoid integer overflow errors when mid * mid > Integer.Max
    // TC: O(logN)
    private static boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }

        long low = 1, high = num / 2;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    // same as above approach except that this avoids longs for variables and using ints, it still avoids integer overflow error
    // TC: O(logN)
    private static boolean isPerfectSquare2(int num) {
        if (num == 1) {
            return true;
        }

        int low = 1, high = num / 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == num / mid && num % mid == 0) {   // mid * mid = num can alternatively be written as mid = num / mid. the second condition (num % mid == 0) is to eliminate false positives where integer division rounds off to lower integer
                return true;
            } else if (mid < num / mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(isPerfectSquare(16));
        assertTrue(isPerfectSquare(25));
        assertTrue(isPerfectSquare(36));
        assertFalse(isPerfectSquare(14));

        assertTrue(isPerfectSquare2(16));
        assertTrue(isPerfectSquare2(25));
        assertTrue(isPerfectSquare2(36));
        assertFalse(isPerfectSquare2(14));
    }
}