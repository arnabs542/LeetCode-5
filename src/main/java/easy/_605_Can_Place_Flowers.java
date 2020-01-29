package easy;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 1/29/20.
 * <p>
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 * </p>
 */
public class _605_Can_Place_Flowers {
    // greedy solution: make the optimal move at every possible move and increment the count when needed
    // greedily place a flower at every vacant spot encountered from left to right
    // TC: O(n)
    private static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                int prev = i == 0 ? 0 : flowerbed[i - 1];
                int next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];

                if (prev == 0 && next == 0) {
                    count++;
                    flowerbed[i] = 1;
                    i++;   // optional: this is just an optimization because if the tree is planted in the current spot, you don't need to check the next spot
                }
            }
        }
        return count >= n;
    }

    public static void main(String[] args) {
        assertTrue(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        assertFalse(canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        assertTrue(canPlaceFlowers(new int[]{0}, 1));
    }
}