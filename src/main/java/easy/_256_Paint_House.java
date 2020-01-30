package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 1/30/20.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 * </p>
 */
public class _256_Paint_House {
    // simple DP approach
    // core logic: greedy approach. keep filling the grid based on the given conditions (curr house color + cost till prev houses with diff colors)
    // dp[i][j] represents the cost of painting the ith house with jth color
    // TC: O(n), SC: O(n)
    private static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int len = costs.length;
        int[][] dp = new int[len][3];   // dp[i][j] represents the cost of painting the ith house with jth color
        dp[0][0] = costs[0][0];         // assume the min costs for the first house
        dp[0][1] = costs[0][1];         // assume the min costs for the first house
        dp[0][2] = costs[0][2];         // assume the min costs for the first house

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];  // cost of painting with current color + min cost of painting till prev house with other colors
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(Math.min(dp[len - 1][0], dp[len - 1][1]), dp[len - 1][2]);
    }

    public static void main(String[] args) {
        assertEquals(minCost(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}), 10);
        assertEquals(minCost(new int[][]{{5, 8, 6}, {19, 14, 13}, {7, 5, 12}, {14, 15, 17}, {3, 20, 10}}), 43);
    }
}