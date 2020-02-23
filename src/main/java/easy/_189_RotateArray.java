package easy;

import java.util.Arrays;

/**
 * Created by udaythota on 7/8/19.
 * <p>
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * </p>
 */
public class _189_RotateArray {
    // core logic: reverse the appropriate parts of the array so as to get the right result
    // for example, nums = [1,2,3,4,5,6,7] and k = 3, first we reverse the whole array, it becomes [7, 6, 5, 4, 3, 2, 1], then we reverse [7, 6, 5], it becomes [5, 6, 7], finally we reverse [4, 3, 2, 1], it becomes [1, 2, 3, 4]. Now the final modified array looks like: [5, 6, 7, 1, 2, 3, 4]
    private static void rotate(int[] nums, int k) {
        k = k % nums.length;  // to handle the cases where k > nums.length
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    // helper function for reversing the array between the given start and end positions
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}