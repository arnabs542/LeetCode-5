package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/4/19.
 * <p>
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * </p>
 */
public class _70_ClimbingStairs {
    // trivial: simple recursion
    // TC: O(2^n) - as we need at least computations for each and every call
    // SC: O(n) -  the depth of the recursion tree can go up to n: each function call takes its own space
    private static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    // DP: with memoization - Top Down approach
    // TC: O(n), SC: O(n)
    private static int climbStairs2(int n) {
        int[] cache = new int[n + 1];
        return helper2(n, cache);
    }

    private static int helper2(int n, int[] cache) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (cache[n] > 0) {
            return cache[n];
        } else {
            cache[n] = helper2(n - 1, cache) + helper2(n - 2, cache);
        }
        return cache[n];
    }

    // DP: Tabulation (Non Recursive approach) - Bottom Up approach
    // TC: O(n), SC: O(n)
    private static int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        for (int i = 3; i < n; i++) {
            dp[n] = dp[n - 1] + dp[n - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assertEquals(climbStairs(2), 2);
        assertEquals(climbStairs(3), 3);

        assertEquals(climbStairs2(2), 2);
        assertEquals(climbStairs2(3), 3);

        assertEquals(climbStairs2(2), 2);
        assertEquals(climbStairs2(3), 3);
    }
}
