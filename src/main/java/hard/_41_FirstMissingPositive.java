package hard;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/11/19.
 * <p>
 * Given an unsorted integer array, find the smallest missing positive integer.
 * </p>
 */
public class _41_FirstMissingPositive {

    // core logic: traverse and try to move the current value to position whose index is exactly the value (swap them). Then traverse again to find the first unusual value, which can not be corresponding to its index.
    //  for each of all the valid numbers (1 to n - where n is the length of the array) in the input array, move them to right indexes (index: 0 - expected element: 1, index: 1 - expected element: 2, index: 2 - expected element: 3, index: 3 - expected element: 4..)
    // each iteration fixes the expected in that index, if there exists one (eg: if i=2 and 3 exists in the array at some other position, this will bring 3 to i=2 position). by end of the loop, you will all have the numbers in their right indices. now iterate through the array again and find the missing number
    private static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // at this point, for all the valid numbers (1 to nums.length) in the array are moved to its right positions
        // iterate through the array and whenever you encounter an index with a wrong number, return the missing (which will be the desired expected) number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;   // all the numbers are in the right places and we reached end of the loop. so the first missing number would be n+1
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        assertEquals(firstMissingPositive(new int[]{1, 2, 0}), 3);
        assertEquals(firstMissingPositive(new int[]{3, 4, -1, 1}), 2);
        assertEquals(firstMissingPositive(new int[]{7, 8, 9, 11, 12}), 1);
        assertEquals(firstMissingPositive(new int[]{8, 3, 2, 4, 1, 9, 6}), 5);
        assertEquals(firstMissingPositive(new int[]{4, 7, 5, 3, 1, 2}), 6);
    }
}
