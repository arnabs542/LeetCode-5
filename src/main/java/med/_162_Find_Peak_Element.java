package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/26/19.
 * <p>
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * </p>
 */
public class _162_Find_Peak_Element {
    // core logic: it is important to note nums[-1] = nums[n] = -∞ and returning any peak is fine.
    // with those assumptions, we can always say rising slope should ALWAYS come down at some point in the array (or at the end of the array)
    // so we can safely turn towards RISING search space at the mid by comparing it to mid+1
    // NOTE: for performing binary search, you DON'T ALWAYS need to have a sorted array. until and unless we have search space and a target and that search space is decreasing, binary search should work
    // better understood with an an example
    private static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        assertEquals(findPeakElement(new int[]{1, 2, 3, 1}), 2);
        assertEquals(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}), 5);
        assertEquals(findPeakElement(new int[]{17, 16, 9, 8, 15, 5, 10}), 6);
    }
}