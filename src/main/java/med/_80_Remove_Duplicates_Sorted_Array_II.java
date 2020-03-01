package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 6/23/19.
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * </p>
 */
public class _80_Remove_Duplicates_Sorted_Array_II {
    // core logic: compare the current element with current-2 element. If its greater (means its a qualified element for the index position), move it to the index position and increment the index
    // TC: O(n)
    private static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int index = 0;  // index represents the position for the next qualified element
        for (int num : nums)
            if (index < 2 || num > nums[index - 2]) {
                nums[index++] = num;
            }
        return index;
    }

    // same as above approach, except that this is generalized to at most k duplicates for each element in an array
    private static int removeDuplicatesAtMostKDups(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        for (int num : nums)
            if (i < k || num > nums[k - 2])
                nums[i++] = num;
        return i;
    }

    public static void main(String[] main) {
        assertEquals(removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}), 5);
        assertEquals(removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}), 7);
        assertEquals(removeDuplicatesAtMostKDups(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}, 3), 9);
    }
}