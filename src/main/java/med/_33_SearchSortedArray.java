package med;

import static org.junit.Assert.assertEquals;

/**
 * Created by udaythota on 4/22/19.
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 */
public class _33_SearchSortedArray {

    // basically a modified version of binary search. if an array is rotated at an unknown index, one side of the index in that is still sorted. so check those conditions wisely.
    // TC: O(logN)
    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) {   // assuming left side of the index is the sorted one
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {   // nums[mid] <= nums[end]   assuming right side of the index is the sorted one
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] test1 = new int[]{5, 6, 7, 1, 2, 3, 4};
        int[] test3 = new int[]{3, 1};

        assertEquals(search(test, 0), 4);
        assertEquals(search(test1, 3), 5);
        assertEquals(search(test3, 1), 1);
    }
}