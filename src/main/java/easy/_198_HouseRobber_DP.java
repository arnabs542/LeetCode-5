package easy;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/8/19.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * </p>
 */
public class _198_HouseRobber_DP {
    /*
       CORE LOGIC: A robber has 2 options: a) rob current house i; b) don't rob current house.
                 If an option "a" is selected it means she can't rob previous i-1 house but can safely proceed to the one before previous i-2 and gets all cumulative loot that follows.
                 If an option "b" is selected the robber gets all the possible loot from robbery of i-1 and all the following buildings.
                 So it boils down to calculating what is more profitable:
                 robbery of current house + loot from houses before the previous loot from the previous house robbery and any loot captured before that
                 rob(i) = Math.max( rob(i - 2) + currentHouseValue, rob(i - 1) )
                 See: https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems. for more details
    */

    // trivial: recursion. TC: O(2^n)
    // top - bottom approach
    private static int rob(int[] nums) {
        return rob(nums, nums.length - 1);
    }

    private static int rob(int[] nums, int index) {
        if (index < 0) {
            return 0;
        }
        return Math.max(rob(nums, index - 1), rob(nums, index - 2) + nums[index]);
    }

    // recursion with memoization. TC: O(n)
    // top - bottom approach
    static int[] memo;

    private static int rob1(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return rob1(nums, nums.length - 1);
    }

    private static int rob1(int[] nums, int index) {
        if (index < 0) {
            return 0;
        }

        if (memo[index] > 0) {
            return memo[index];
        }
        int result = Math.max(rob1(nums, index - 1), rob1(nums, index - 2) + nums[index]);
        memo[index] = result;
        return result;
    }

    // iterative with memo (bottom - up)
    // NOTE: use this solution as the same will be used in 213 - house robber II as well
    private static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] memo = new int[nums.length + 1];
        memo[0] = 0;
        memo[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            memo[i + 1] = Math.max(memo[i], memo[i - 1] + current);
        }
        return memo[nums.length];
    }

    // iterative with memo (bottom - up)
    // replace memo array with 2 variables as we only need current and prev memoized values at any point of time
    private static int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prevMem = 0;
        int currentMem = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = currentMem;
            currentMem = Math.max(currentMem, prevMem + nums[i]);
            prevMem = temp;
        }
        return currentMem;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(rob(new int[]{1, 2, 3, 1}), 4);
        assertEquals(rob(new int[]{2, 7, 9, 3, 1}), 12);

        // test method: 2
        assertEquals(rob1(new int[]{1, 2, 3, 1}), 4);
        assertEquals(rob1(new int[]{2, 7, 9, 3, 1}), 12);

        // test method: 3
        assertEquals(rob2(new int[]{1, 2, 3, 1}), 4);
        assertEquals(rob2(new int[]{2, 7, 9, 3, 1}), 12);

        // test method: 4
        assertEquals(rob3(new int[]{1, 2, 3, 1}), 4);
        assertEquals(rob3(new int[]{2, 7, 9, 3, 1}), 12);
    }
}