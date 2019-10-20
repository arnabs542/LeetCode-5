package _5_Longest_Palindromic_Substring;

import static org.junit.Assert.assertEquals;

/**
 * Created by udaythota on 4/23/19.
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).You are given a target value to search. If found in the array return true, otherwise return false.
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * <p>
 */
public class _81_SearchSortedArrayWithDups {

    private static boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {   // NOTE: checking 2 conditions is redundant, but this is just for the sake of some performance improvement
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, here we just use end-- but left++ works too
            } else {
                end--;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] test = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] test1 = new int[]{2, 5, 6, 0, 0, 1, 2};
        int[] test2 = new int[]{1, 3, 1, 1, 1};

        assertEquals(search(test, 0), true);
        assertEquals(search(test1, 3), false);
        assertEquals(search(test2, 3), true);
    }
}
