package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/6/19.
 * <p>
 * Given an integer n, return the number of trailing zeroes in n!.
 * </p>
 */
public class _172_Factorial_Trailing_Zeroes {
    // core logic: The idea is to count the number of 5 factors in all numbers from 1 to n.
    // Since all trailing 0's are from factors 2 * 5. In n! operation, factors 2 will always be more than factors 5. So we just count the number of 5 factors in all numbers from 1 to n.
    // Example: There are 20 multiples of 5 from 1 to 100, since 100 / 5 = 20. But actually 25 is 5 * 5, so each multiple of 25 has an extra factor of 5 which should also be counted. Since 100 / 25 = 4, there are four multiples of 25 between 1 and 100. Finally, we get 20 + 4 = 24 trailing zeros in 100!.
    // Therefore, we need care about 5 powers as well, to consider additional 5 factors.
    // So it can be computed as:
    // count = (n / 5) + (n / 25) + (n / 125) + ... + 0. Iteration will be terminated when 5^k becomes greater than n.
    // TC: O(logN)
    private static int trailingZeroes(int n) {
        int result = 0;
        while (n != 0) {
            result += n / 5;
            n = n / 5;
        }
        return result;
    }

    private static int trailingZeroesRecursive(int n) {
        if (n < 5) {
            return 0;
        } else {
            return n / 5 + trailingZeroesRecursive(n / 5);
        }
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(trailingZeroes(3), 0);
        assertEquals(trailingZeroes(5), 1);
        assertEquals(trailingZeroes(50), 12);

        // test method: 2
        assertEquals(trailingZeroesRecursive(3), 0);
        assertEquals(trailingZeroesRecursive(5), 1);
    }
}