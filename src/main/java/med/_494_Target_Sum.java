package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/13/20.
 * <p>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * </p>
 */
public class _494_Target_Sum {
    private static int result;

    // core logic: simple DFS exploring all the possibilities ('+' and '-')
    // TC: O(2^n) -> as for every path we have 2 possibilities
    // TODO: room for optimization. deal with them later. see the memoized approach here: https://leetcode.com/problems/target-sum/discuss/169648/Java-DFS-and-Memorization-with-Explanations
    private static int findTargetSumWays(int[] nums, int target) {
        result = 0;
        dfsHelper(nums, target, 0, 0);
        return result;
    }

    private static void dfsHelper(int[] nums, int target, int pos, int currResult) {
        if (pos == nums.length) {
            if (currResult == target) {
                result++;
            }
            return;
        }
        dfsHelper(nums, target, pos + 1, currResult + nums[pos]);  // explore '+' possibility
        dfsHelper(nums, target, pos + 1, currResult - nums[pos]);  // explore '-' possibility
    }

    public static void main(String[] args) {
        Assert.assertEquals(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3), 5);
    }
}
