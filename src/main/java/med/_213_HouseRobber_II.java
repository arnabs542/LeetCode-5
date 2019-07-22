package med;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/6/19.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * </p>
 */
public class _213_HouseRobber_II {
    // trivial: recursion. TC: O(2^n)
    // core logic: the only difference for this when compared to house robber-I is that houses are circular here (meaning, first and last houses shouldn't be summed)
    // so divide the given input array to 2 input arrays (one which includes the first element and an other one without including it), do the same solution on both inputs, compare both the max and return the max out of them
    private static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {   // this is to handle the single element in an array case (as without it, we cannot break the input array without first element part)
            return nums[0];
        }

        int[] numsWithFirstElement = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] numsWithoutFirstElement = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(robHelper(numsWithFirstElement), robHelper(numsWithoutFirstElement));
    }

    // iterative with memo (bottom - up)
    private static int robHelper(int[] nums) {
        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            memo[i + 1] = Math.max(memo[i], memo[i - 1] + current);
        }
        return memo[nums.length];
    }

    public static void main(String[] args) {
        assertEquals(rob(new int[]{2, 3, 2}), 3);
        assertEquals(rob(new int[]{1, 2, 3, 1}), 4);
        assertEquals(rob(new int[]{1, 2, 1, 1}), 3);
    }
}
