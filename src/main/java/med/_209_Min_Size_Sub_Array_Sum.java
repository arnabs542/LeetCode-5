package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * </p>
 */
public class _209_Min_Size_Sub_Array_Sum {
    // core logic: sliding window and 2 pointer approach.
    // TC: O(n)
    private static int minSubArrayLen(int requiredSum, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0, j = 0, currentSum = 0, minLength = Integer.MAX_VALUE;   // j is a fast pointer while i being slow pointer

        while (j < nums.length) {
            currentSum += nums[j];
            while (currentSum >= requiredSum) {
                minLength = Math.min(minLength, j - i + 1);
                currentSum -= nums[i++];
            }
            j++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        assertEquals(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}), 2);
        assertEquals(minSubArrayLen(11, new int[]{2, 9, 1, 2, 4, 3}), 2);
    }
}