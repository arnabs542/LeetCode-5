package easy;

/**
 * Created by udaythota on 6/6/19.
 * <p>
 * Given an integer array nums, find the contiguous sub array (containing at least one number) which has the largest sum and return its sum.
 * </p>
 */
public class _53_MaxSubArray {
    // DP: save the current max for each element (till that element) and reset the result when needed
    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], nums[i] + max);  // tracks the max sum till the current element

            if (max > result) {  // reset result: encountered new max
                result = max;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(input));
    }
}