package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/6/19.
 * <p>
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * </p>
 */
public class _152_Max_Product_Subarray {

    // DP: Top to bottom approach
    // core logic: for each and every element, calculate the current max and current min till that element. if the current max is greater than result, set result to current max
    // the reason current min should be tracked is that current min * current value could be the max (when both of those are negative elements)
    private static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0], max = nums[0], result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int tempMax = max; // save the current max as it is being modified in the next step
            max = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i], Math.min(nums[i] * min, nums[i] * tempMax)));

            if (max > result) {  // set the result to max when you see a bigger current max
                result = max;
            }
        }
        return result;
    }

    // DP: exactly same as above approach except slight tweaking in the code to make it more readable
    // handles negative and positive elements differently
    private static int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums[0], max = nums[0], result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max = Math.max(nums[i], nums[i] * max);
                min = Math.min(nums[i], nums[i] * min);
            } else {
                int tempMax = max;
                max = Math.max(nums[i], min * nums[i]);   // for negative numbers, current max could be (current max min * nums[i])
                min = Math.min(nums[i], tempMax * nums[i]); // for negative numbers, current min could be (current min max * nums[i])
            }

            if (max > result) {  // set the result to max when you see a bigger current max
                result = max;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, -2, 4};
        assertEquals(maxProduct(input), 6);
        assertEquals(maxProduct2(input), 6);

        int[] input1 = new int[]{-2, 0, -1};
        assertEquals(maxProduct(input1), 0);
        assertEquals(maxProduct2(input1), 0);
    }
}
