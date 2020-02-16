package med;

import java.util.Arrays;

/**
 * Created by udaythota on 2/15/20.
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * </p>
 */
public class _31_Next_Possible_Permutation {
    // core logic: the meat of the problem lies in the observation that for any decreasing sequence, there cannot be a bigger possible permutation (eg: 4321: there can't be a bigger possible number than this)
    // so start scanning from right of the array and find the first possible non decreasing sequence index (nums[i] < nums[i]+1). now we need to swap this element (ith index) with the next bigger number from on its right (i+1 to end)
    // after swapping, it is still not guaranteed the current sequence is the next possible sequence, so we need to sort all the element right to i (which is equivalent to reversing as i+1th to last element are already in descending order)
    // see here: https://leetcode.com/problems/next-permutation/solution/ if you still need some more explanation
    // TC: O(n) - in place - modifying the input array
    private static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int i, j;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {   // found the first index from right which satisfies the non decreasing condition: so break the loop
                break;
            }
        }

        if (i >= 0) {
            for (j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {  // found the smallest next largest element from the right side
                    break;
                }
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);   // ascending sort (equivalent to reverse in this case) to guarantee the next possible largest permutation
    }

    // helper method to reverse all the elements between given 2 indexes
    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    // swap the elements on the given indices
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 3};
        nextPermutation(input1);
        System.out.println(Arrays.toString(input1));

        int[] input2 = new int[]{3, 2, 1};
        nextPermutation(input2);
        System.out.println(Arrays.toString(input2));

        int[] input3 = new int[]{1, 1, 5};
        nextPermutation(input3);
        System.out.println(Arrays.toString(input3));
    }
}