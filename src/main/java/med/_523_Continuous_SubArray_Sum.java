package med;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 8/16/19.
 * <p>
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous sub array of size at least 2
 * that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 * </p>
 */
public class _523_Continuous_SubArray_Sum {
    private static boolean checkSubArraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int runningSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();   // to keep track of current running sum (till ith index) : next time we encounter this running sum this means that there is a possible sum
        map.put(0, -1);   // return true when the runningSum % k = 0
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum = runningSum % k;
            }

            Integer prev = map.get(runningSum);
            if (prev != null) {   // if the same running total has encountered again
                if (i - prev > 1) {   // to make sure the size of sub array is > 2
                    return true;
                }
            } else {
                map.put(runningSum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(checkSubArraySum(new int[]{23, 2, 4, 6, 7}, 6));
        assertTrue(checkSubArraySum(new int[]{23, 2, 6, 4, 7}, 6));
        assertTrue(checkSubArraySum(new int[]{23, 2, 4, 6, 7}, -6));
        assertTrue(checkSubArraySum(new int[]{23, 1}, 6));
        assertTrue(checkSubArraySum(new int[]{23, 2, 6, 4}, 6));
        assertFalse(checkSubArraySum(new int[]{2, 6}, 6));
        assertTrue(checkSubArraySum(new int[]{10, 8, 4, 5}, 6));
    }
}