package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/24/19.
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * </p>
 */
public class _121_BestTimeToBuyStock {
    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    public static void main(String[] args) {
        assertEquals(maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 5);
        assertEquals(maxProfit(new int[]{7, 6, 4, 3, 1}), 0);
    }
}
