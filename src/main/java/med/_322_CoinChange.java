package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/11/19.
 * <p>
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * </p>
 */
public class _322_CoinChange {
    private static int coinChange(int[] coins, int amount) {
        return 0;
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 5};
        assertEquals(coinChange(input1, 11), 3);

        int[] input2 = new int[]{2};
        assertEquals(coinChange(input2, 3), -1);
    }
}
