package med;

import java.util.Arrays;

/**
 * Created by udaythota on 4/19/20.
 * <p>
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library's sort function for this problem.
 * </p>
 */
public class _75_Sort_Colors {
    // one pass solution: make sure to increment i only when needed (we don't increment in the 2nd case)
    // TC: O(n)
    private static void sortColors(int[] nums) {
        int red = 0, blue = nums.length - 1, index = 0;
        for (int i = 0; i <= blue; ) {
            if (nums[i] == 0) {
                swap(nums, red, i);
                i++;
                red++;
            } else if (nums[i] == 2) {   // NOTE: i is not incremented here: since when 2 could be swapped with 0 and we again need to swap 0 to the right position. eg: [1,2,0] -> [1,0,2]
                swap(nums, i, blue);
                blue--;
            } else {
                i++;
            }
        }
    }

    // 2 pass solution: first pass to count the colors, second pass to fill the input array accordingly based on the counts
    // TC: O(n)
    private static void sortColors2(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for (int num : nums) {
            if (num == 0) {
                red++;
            } else if (num == 1) {
                white++;
            } else {
                blue++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < red) {
                nums[i] = 0;
            } else if (i < red + white) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }


    // simple swap helper function
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // 1st solution
        int[] input = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(input);
        System.out.println(Arrays.toString(input));

        // 2nd solution
        int[] input2 = new int[]{2, 0, 2, 1, 1, 0};
        sortColors2(input2);
        System.out.println(Arrays.toString(input2));
    }
}
