package hard;

import java.util.HashSet;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/5/20.
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 * </p>
 */
public class _128_Longest_Consecutive_Sequence {
    // core logic: transfer all the numbers to a set. for every number in input array, count the number of consecutive numbers to its left and to its right and keep removing them from the set. update the longest count as needed
    // TC: O(n), SC: O(n)
    private static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int longest = Integer.MIN_VALUE;
        int currLongest;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i++) {
            currLongest = 0;
            int currNumber = nums[i];
            while (set.contains(currNumber)) {  // look left (lesser) consecutive numbers
                set.remove(currNumber);
                currNumber--;
                currLongest++;
            }

            currNumber = nums[i] + 1;
            while (set.contains(currNumber)) {  // look right (greater) consecutive numbers
                set.remove(currNumber);
                currNumber++;
                currLongest++;
            }
            longest = Math.max(longest, currLongest);  // update longest at the end of each iteration
        }
        return longest;
    }

    public static void main(String[] args) {
        assertEquals(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}), 4);
        assertEquals(longestConsecutive(new int[]{-1, 1, 0}), 3);
    }
}