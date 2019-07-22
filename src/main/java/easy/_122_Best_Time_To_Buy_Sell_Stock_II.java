package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/27/19.
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * </p>
 */
public class _122_Best_Time_To_Buy_Sell_Stock_II {

    // core logic: whenever you encounter a number greater than previous, calculate the profit and keep a count of it
    private static int maxProfit(int[] prices) {
        int maxProfit = 0;

        if (prices == null || prices.length == 0) {
            return 0;
        }

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    // core logic: get the buying price (when the current price < next price), get the selling price (when the next price > current price). calculate the profit and keep a count of it
    private static int maxProfit2(int[] prices) {
        int maxProfit = 0, buy, sell;
        int i = 0;

        if (prices == null || prices.length == 0) {
            return 0;
        }
        int pricesLength = prices.length - 1;

        while (i < pricesLength) {
            while (i < pricesLength && prices[i + 1] <= prices[i]) {
                i++;
            }
            buy = prices[i];

            while (i < pricesLength && prices[i + 1] > prices[i]) {
                i++;
            }
            sell = prices[i];
            maxProfit += sell - buy;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 7);
        assertEquals(maxProfit(new int[]{1, 2, 3, 4, 5}), 4);
        assertEquals(maxProfit(new int[]{7, 6, 4, 3, 1}), 0);

        // test method: 2
        assertEquals(maxProfit2(new int[]{7, 1, 5, 3, 6, 4}), 7);
        assertEquals(maxProfit2(new int[]{1, 2, 3, 4, 5}), 4);
        assertEquals(maxProfit2(new int[]{7, 6, 4, 3, 1}), 0);
    }
}