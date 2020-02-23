package easy;

import java.util.Arrays;

/**
 * Created by udaythota on 7/14/19.
 * <p>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * </p>
 */
public class _283_Move_Zeroes {
    // core logic: maintain the true insertion index which keeps track of indexes to insert all the non zero numbers
    // Shift non-zero values as far forward as possible
    // Fill remaining space with zeros
    // TC: O(n)
    private static void moveZeroes(int[] nums) {
        int insertIndex = 0;
        for (int num : nums) {
            if (num != 0) {   // when a non zero number is encountered, place the number in the insert index position and increment the index
                nums[insertIndex] = num;
                insertIndex++;
            }
        }
        while (insertIndex < nums.length) {  // fill all the remaining indexes with zero
            nums[insertIndex] = 0;
            insertIndex++;
        }
    }

    // core logic: whenever you encounter a non zero number, swap the number to its expected insert index position and increment the index. finally the zeroes are automatically pushed / left over at the end
    private static void moveZeroes2(int[] nums) {
        int insertIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {     // swap all the non zero numbers to the insertIndex positions
                int temp = nums[i];
                nums[i] = nums[insertIndex];
                nums[insertIndex] = temp;
                insertIndex++;
            }
        }
    }

    public static void main(String[] args) {
        // test method: 1
        int[] input = new int[]{0, 1, 0, 3, 12};
        moveZeroes(input);
        System.out.println(Arrays.toString(input));

        // test method: 2
        int[] input2 = new int[]{0, 1, 0, 3, 12};
        moveZeroes2(input2);
        System.out.println(Arrays.toString(input2));
    }
}