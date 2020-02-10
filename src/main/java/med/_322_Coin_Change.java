package med;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/9/20.
 * <p>
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * </p>
 */
public class _322_Coin_Change {
    // classic DP: bottom up approach
    // TC: O(m*n) -> where m is the length of coins and n is the amount
    // NOTE: see https://www.youtube.com/watch?v=Y0ZqKpToTic for understanding the idea
    private static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /*private static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        int[][] dp = new int[coins.length][amount + 1];
        for (int j = 0; j <= amount; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0) {
                    dp[i][j] = 0;
                }
                if (j >= coins[i]) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }*/

    public static void main(String[] args) {
        assertEquals(coinChange(new int[]{1, 2, 5}, 11), 3);
        assertEquals(coinChange(new int[]{2}, 3), -1);
    }
}