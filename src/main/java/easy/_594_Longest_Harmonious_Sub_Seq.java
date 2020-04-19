package easy;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by udaythota on 4/18/20.
 * <p>
 * We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 * </p>
 */
public class _594_Longest_Harmonious_Sub_Seq {
    // the meat of the problem lies in coming up with this observation: assume each number in the array could be a min value. the max value could only be min+1 to be valid LHS, as the max diff should always be 1. hence, get the counts of nums[min] and nums[min+1] and update the max as needed
    // TC: O(n), SC: O(n)
    private static int findLHS(int[] nums) {
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            if (map.containsKey(num + 1)) {
                max = Math.max(max, map.get(num) + map.get(num + 1));
            }
        }
        return max;
    }

    // core logic: sorting + sliding window solution. as we navigate the array, update the max when we see the diff between elements == 1 and continue. update the start and end pointers as needed
    // TC: O(nlogn) due to sorting
    private static int findLHS2(int[] nums) {
        Arrays.sort(nums);
        int start = 0, end = 0, max = 0;
        while (end < nums.length) {
            int diff = nums[end] - nums[start];
            if (diff == 1) {   // valid LHS, so update the max as needed and go forward
                max = Math.max(max, end - start + 1);
                end++;
            } else if (diff == 0) {   // same element, so go forward in the array
                end++;
            } else {   // diff > 1, which means we need to increment the start / min value (note that diff can never be <1 as we have sorted the array)
                start++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}), 5);
        Assert.assertEquals(findLHS2(new int[]{1, 3, 2, 2, 5, 2, 3, 7}), 5);
    }
}
