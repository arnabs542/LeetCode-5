package med;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/26/19.
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * </p>
 */
public class _153_Find_Minimum_Sorted_Array {
    // core logic: for any mid, if A[mid] > A[n - 1], then the minimum value must be an index in the range [mid + 1, n - 1].
    // conversely, if A[mid] < A[n - 1], the no index in the range [mid + 1, n - 1] can be the index of the minimum value.
    // minimum value may be at A[mid] or its index in the range [0, mid].
    private static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {   // this means right side is faulty (rotation happened to right), so the min element should be in the right
                low = mid + 1;
            } else {
                high = mid;   // min element should be to left
            }
        }
        return nums[low];   // loop ends when low = high, so return nums[low] or nums[high]
    }

    public static void main(String[] args) {
        assertEquals(findMin(new int[]{3, 4, 5, 1, 2}), 1);
        assertEquals(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}), 0);
    }
}