package _5_Longest_Palindromic_Substring;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/3/19.
 * <p>
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k
 * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * </p>
 */
public class _259_3Sum_Smaller {
    // core logic: sort the array. use 2 pointers to check for all the possible targets
    // NOTE: when i + j + k <= target, all the combinations in between j and k also satisfy the same condition (as array is already sorted). so add all those to count
    // TC: O(n^2)
    private static int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        if (nums == null || nums.length == 0) {
            return count;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    count += right - left;   // as all the combinations in (l, r] will also satisfy the same combination (as array is sorted)
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(threeSumSmaller(new int[]{-2, 0, 1, 3}, 2), 2);
    }
}