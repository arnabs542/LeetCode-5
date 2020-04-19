package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/16/20.
 * <p>
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * </p>
 */
public class _167_Two_Sum_II {
    // 2 pointers solution as array is already sorted
    // TC: O(n)
    private static int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Assert.assertEquals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{1, 2});
    }
}
