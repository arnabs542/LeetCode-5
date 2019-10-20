package _5_Longest_Palindromic_Substring;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/9/19.
 * <p>
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * </p>
 */
public class _264_UglyNumber_II {

    // DP: iterative with bottom up approach
    // core logic: start with smallest common prime factor (1) for the possible divisors (2, 3, 5) and keep incrementing them as needed
    // dp[i] represents the smallest ith ugly number
    private static int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(2 * dp[p2], Math.min(3 * dp[p3], 5 * dp[p5]));  // the next ugly number must be built from a smaller ugly number
            if (dp[i] == 2 * dp[p2]) {
                p2++;
            }
            if (dp[i] == 3 * dp[p3]) {
                p3++;
            }

            if (dp[i] == 5 * dp[p5]) {
                p5++;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assertEquals(nthUglyNumber(10), 12);
        assertEquals(nthUglyNumber(11), 15);
        assertEquals(nthUglyNumber(13), 18);
    }
}
