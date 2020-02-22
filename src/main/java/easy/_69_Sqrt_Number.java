package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/11/20.
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * </p>
 */
public class _69_Sqrt_Number {
    // core logic: classic binary search
    // TC: O(logN)
    private static int mySqrt(int x) {
        if (x <= 1) {
            return 1;
        }

        int low = 1, high = x / 2;   // as the square root of a number always lies between 1 and n/2
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == x / mid && x % mid == 0) {   // make sure the number is exactly divisible (check the remainder)
                return mid;
            } else if (mid > x / mid) {   // don't write it as mid * mid as it might lead to integer overflow
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        assertEquals(mySqrt(16), 4);
        assertEquals(mySqrt(9), 3);
        assertEquals(mySqrt(10), 3);
        assertEquals(mySqrt(81), 9);
        assertEquals(mySqrt(121), 11);
        assertEquals(mySqrt(8), 2);
        assertEquals(mySqrt(2147395599), 46339);
    }
}