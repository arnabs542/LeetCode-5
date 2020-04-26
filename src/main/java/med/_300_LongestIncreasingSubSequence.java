package med;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/9/19.
 * <p>
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * </p>
 */
public class _300_LongestIncreasingSubSequence {
    // DP: Non Recursive Min_Hours_Challenge
    // core logic: get the longest increasing sub sequences for the given index from all the possible indexes and update the dp array when needed
    // approach taken from: https://www.youtube.com/watch?v=CE2b_-XfVDk
    // TC: O(n^2)
    // TODO: there are more optimal approaches. think of them
    private static int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, 1);

        // Mark one pointer at i. For each i, start from j=0 and iterate till i-1
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // It means next number contributes to increasing sequence.
                if (nums[i] > nums[j]) {
                    // But increase the value only if it results in a larger value of the sequence than T[i]
                    // It is possible that T[i] already has larger value from some previous j'th iteration
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        // Find the maximum length from the array that we just generated
        int longest = 0;
        for (int i = 0; i < dp.length; i++) {
            longest = Math.max(longest, dp[i]);
        }
        return longest;
    }

    public static void main(String[] args) {
        assertEquals(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}), 4);
    }
}
