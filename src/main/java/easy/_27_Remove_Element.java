package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/23/20.
 * <p>
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * </p>
 */
public class _27_Remove_Element {
    // core logic: keep an index which tracks / inserts non val elements to its right indices.
    // follow up: if you rather want to push all the elements with val to the end of the array, have a swap function which swaps the val element to the end whenever it encounters an element with that value
    // TC: O(n), SC: O(1)
    private static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        assertEquals(removeElement(new int[]{3, 2, 2, 3}, 3), 2);
        assertEquals(removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2), 5);
    }
}