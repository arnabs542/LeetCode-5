package easy;

import java.util.HashMap;
import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/2/20.
 * <p>
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * </p>
 */
public class _496_Next_Greater_Element_I {
    // the meat of the problem lies in using the stack and map in the right way. we here use map it to store element and its next higher element (for all element in nums2). then we iterate through the nums1 to get its next higher elements
    // use of stack: we use this to keep a decreasing sub sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x
    // see here for further explanation: https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
    // TC: O(n)
    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> nextHighestElements = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                nextHighestElements.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nextHighestElements.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }

    public static void main(String[] args) {
        assertEquals(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}), new int[]{-1, 3, -1});
        assertEquals(nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}), new int[]{3, -1});
    }
}