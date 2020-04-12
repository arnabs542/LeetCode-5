package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * </p>
 */
public class _303_Range_Query_Sum_Immutable {
    private int[] dp;

    // build the dp array and use that to calculate the required sums later
    // can also be done in place by modifying tht input array: https://leetcode.com/problems/range-sum-query-immutable/discuss/75192/Java-simple-O(n)-init-and-O(1)-query-solution
    private _303_Range_Query_Sum_Immutable(int[] nums) {
        dp = new int[nums.length + 1];
        // dp[0] = 0;  // not needed as by default all values will be zeroes
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }

        /*for (int i = 0; i < dp.length; i++) {   // equivalent to the above loop
            dp[i + 1] = dp[i] + nums[i];
        }*/
    }

    private int sumRange(int i, int j) {
        return dp[j + 1] - dp[i];
    }

    public static void main(String[] args) {
        _303_Range_Query_Sum_Immutable range_query_sum_immutable = new _303_Range_Query_Sum_Immutable(new int[]{-2, 0, 3, -5, 2, -1});
        Assert.assertEquals(range_query_sum_immutable.sumRange(0, 2), 1);
        Assert.assertEquals(range_query_sum_immutable.sumRange(2, 5), -1);
        Assert.assertEquals(range_query_sum_immutable.sumRange(0, 5), -3);
    }
}
