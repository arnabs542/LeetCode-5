package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
 * </p>
 */
public class _724_Find_Pivot_Index {
    // brute force: as every index could be a possible candidate for pivot index, calculate left and right sums at every index and return the first index where those sums match
    // Tc: O(n^2)
    private static int pivotIndex(int[] nums) {
        int start = 0, end = nums.length - 1;
        int pivotIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            int leftMax = 0, rightMax = 0;
            int j = i + 1;
            while (j <= end) {
                rightMax += nums[j++];
            }

            int k = i - 1;
            while (k >= start) {
                leftMax += nums[k--];
            }
            if (leftMax == rightMax) {
                return i;   // return the first pivot index where left sum = right sum
            }
        }
        return pivotIndex;
    }

    // simple and wonderful 2 pass solution: in the first pass calculate the sum of entire array. in the second pass, keep calculating left sum and use total sum, left sum and pivot sum to calculate the right sum and compare them
    // sum = leftSum + rightSum + pivotSum, so: rightSum = sum - leftSum - pivotSum (pivotSum = nums[i] at every i in the loop)
    // TC: O(n)
    private static int pivotIndex2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int sum = 0, leftSum = 0, rightSum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                leftSum += nums[i - 1];
            }
            rightSum = sum - nums[i] - leftSum;  // nums[i] is the pivot for every i in the loop
            if (leftSum == rightSum) {
                return i;  // return the first pivot index where left sum = right sum
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // solution 1
        Assert.assertEquals(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}), 3);
        Assert.assertEquals(pivotIndex(new int[]{1, 2, 3}), -1);

        // solution 2
        Assert.assertEquals(pivotIndex2(new int[]{1, 7, 3, 6, 5, 6}), 3);
        Assert.assertEquals(pivotIndex2(new int[]{1, 2, 3}), -1);
    }
}
