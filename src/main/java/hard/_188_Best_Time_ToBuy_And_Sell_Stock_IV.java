package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/27/19.
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * </p>
 */
public class _188_Best_Time_ToBuy_And_Sell_Stock_IV {

    // core logic: i represents the transactions and j represents the number of transaction days
    // this logic fails with memory limit exceeded for few corner cases in LC, but it's still valid
    // see https://www.youtube.com/watch?v=oDhu5uGq_ic for more details
    private static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 0) {
            return 0;
        }

        int[][] matrix = new int[k + 1][prices.length];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                int maxProfit = 0;
                for (int m = 0; m < j; m++) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[m] + matrix[i - 1][m]);
                }
                matrix[i][j] = Math.max(maxProfit, matrix[i][j - 1]);
            }
        }
        return matrix[k][prices.length - 1];
    }

    public static void main(String[] args) {
        assertEquals(maxProfit(2, new int[]{2, 4, 1}), 2);
        assertEquals(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}), 7);
    }
}
