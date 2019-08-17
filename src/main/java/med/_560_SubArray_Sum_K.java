package med;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/17/19.
 * <p>
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * </p>
 */
public class _560_SubArray_Sum_K {

    // core logic: check for all the possible combinations. when you encounter the desired sum, increment the count
    // TC: O(n^2)
    private static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];

                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // core logic: maintain a map to keep the sum of all the possible sub array combinations (nums[0], nums[0]+nums[1], nums[0]+nums[1]+nums[2], nums[0]+nums[1]+nums[2]+nums[3].. )
    // use this as a look up map to check if it has (sum - k) element. see https://leetcode.com/articles/subarray-sum-equals-k/ for more details
    // TC: O(n), SC: O(n)
    private static int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0, sum = 0;
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (preSumMap.containsKey(sum - k)) {
                count += preSumMap.get(sum - k);    // value at the sum index represents the number of possible sub array sums that could be possible till the current index
            }
            preSumMap.put(sum, preSumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        assertEquals(subarraySum(new int[]{1, 1, 2}, 2), 2);
        assertEquals(subarraySum2(new int[]{1, 2, 3, 5, 4}, 12), 1);
    }
}