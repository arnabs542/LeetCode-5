package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/23/19.
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * </p>
 */
public class _26_Remove_Duplicates_SortedArray {

    // core logic: index tracks the actual position of elements which are supposed to be in the new array
    // basically, as the array is already sorted, we can assume the actual index of elements without scanning the next elements in the array
    private static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int index = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[index]) {    // increment 'j' till the current index element is repeated
                index++;
                nums[index] = nums[j];
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        assertEquals(removeDuplicates(new int[]{1, 1, 2}), 2);
        assertEquals(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}), 5);
        assertEquals(removeDuplicates(new int[]{0, 0, 1, 1, 2, 3, 4}), 5);
    }
}
