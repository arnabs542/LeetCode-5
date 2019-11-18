package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/17/19.
 * <p>
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * </p>
 */
public class _96_Unique_BSTs_Possible {
    // core logic: DP: calculate the BST's at each and every step and keep building on top of that
    // eg: for every number as root in 1 to n, calculate the possible number of left and right sub trees to get the total count of valid BST's
    // this is also known as finding the nth catalan number
    // TC: O(n^2), SC: O(n)
    private static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];   // * not + -> as for every left possibility there are n number of right possibilities / combinations, so the total number is product of both
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        assertEquals(numTrees(3), 5);
        assertEquals(numTrees(4), 14);
        assertEquals(numTrees(5), 42);
    }
}