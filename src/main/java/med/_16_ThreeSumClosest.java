package med;

import org.testng.Assert;

import java.util.Arrays;

/**
 * <p>
 * Created by udaythota on 4/11/19.
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * </p>
 */
public class _16_ThreeSumClosest {
    private static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int closestSum = nums[0] + nums[1] + nums[2];   // NOTE: don't use integer max to initialize as the absolute value could go more than that which might cause un-wanted results
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int firstPointer = i + 1;
            int endPointer = nums.length - 1;

            while (firstPointer < endPointer) {
                int currentSum = nums[i] + nums[firstPointer] + nums[endPointer];
                if (currentSum > target) {
                    endPointer--;    // when the current sum is greater than given target, decrease the sum by decrementing the end pointer
                } else {
                    firstPointer++;   // when the current sum is less than given target, increase the sum by incrementing the first pointer
                }

                // update the result, when the distance (absolute) between the current sum and target is less than the previous result and target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        Assert.assertEquals(threeSumClosest(nums, target), 2);

        int[] nums1 = {1, 1, -1, -1, 3};
        int target2 = -1;
        Assert.assertEquals(threeSumClosest(nums1, target2), -1);

        Assert.assertEquals(threeSumClosest(null, -2), -1);
    }
}