package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/3/20.
 * <p>
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 * </p>
 */
public class _1060_Missing_Element_Sorted_Array {
    // core logic: uses binary search: meat of the problem lies in the observation that number of missing numbers between nums[left] and nums[right] in a sorted array is nums[right] - nums[left] - (right-left)
    // TC: O(logN) because of binary search
    // see if needed: https://leetcode.com/problems/missing-element-in-sorted-array/discuss/477727/Java-Binary-Search-faster-than-100.00
    private static int missingElement(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        int missingNum = nums[right] - nums[left] - (right - left);
        if (k > missingNum) {  // kth missing largest element would be to the right of the last element
            return nums[right] + k - missingNum;
        }

        while (left + 1 < right) {   // there should be at least one element diff between left and right indexes when the loop ends
            int mid = left + (right - left) / 2;
            int missingLeft = nums[mid] - nums[left] - (mid - left);
            if (k > missingLeft) {
                k -= missingLeft;   // as we are moving the left pointer, decrement the missing numbers in the left window from k and repeat the process
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] + k;  // which means that the missing number should be between the final left and right indices and at kth distance from the left index
    }

    public static void main(String[] args) {
        assertEquals(missingElement(new int[]{4, 7, 9, 10}, 1), 5);
        assertEquals(missingElement(new int[]{4, 7, 9, 10}, 3), 8);
        assertEquals(missingElement(new int[]{1, 2, 4}, 3), 6);
    }
}