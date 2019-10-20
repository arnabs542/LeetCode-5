package _5_Longest_Palindromic_Substring;

import java.util.Arrays;

/**
 * Created by udaythota on 5/14/19.
 * <p>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * </p>
 */
public class _34_FirstAndLastPositionSortedArray {

    // core logic is to find the start and end indexes of the target (each time using a modified binary search approach)
    // for the start index: strict binary search: until the mid element is STRICTLY LESS than target, keep DECREASING the right pointer (so we could go to the left most target)
    // for the end index: loose binary search: until the mid element is LESS THAN OR EQUAL to target, keep INCREASING the left pointer (so we could go to the right most target)
    // update the index whenever you see the target (nums[mid]==target), so the function could return the last seen (either first or last) index
    private static int[] searchRange(int[] nums, int target) {
        int start = findPosition(nums, target, false);
        int end = findPosition(nums, target, true);
        return new int[]{start, end};
    }

    private static int findPosition(int[] nums, int target, boolean isEnd) {
        int low = 0, high = nums.length - 1, index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isEnd) {
                if (nums[mid] <= target) low = mid + 1;
                else high = mid - 1;
            } else {
                if (nums[mid] < target) low = mid + 1;
                else high = mid - 1;
            }
            if (nums[mid] == target) index = mid; // update index whenever you see the target
        }
        return index;
    }

    public static void main(String[] args) {
        int[] result = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        int[] result1 = searchRange(new int[]{5, 8, 8, 8, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result1));
    }
}
