package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * </p>
 */
public class _485_Max_Consecutive_Ones {
    // core logic: sliding window solution. make sure that in any sliding window, we always have 0 zeroes.
    // exact same as LC: 1004 and in fact similar to most of the sliding window solutions
    // TC: O(n)
    private static int findMaxConsecutiveOnes(int[] nums) {
        int start = 0, max = 0, zeroesCount = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                zeroesCount++;
            }
            while (zeroesCount > 0) {
                if (nums[start] == 0) {
                    zeroesCount--;
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    // alternate solution: straight forward. keep the count of ones and reset them whenever you see a zero. keep repeating this till the end
    // TC: O(n)
    private static int findMaxConsecutiveOnes2(int[] nums) {
        int max = 0, onesCount = 0;
        for (int num : nums) {
            if (num == 1) {
                onesCount++;
            } else {
                onesCount = 0;
            }
            max = Math.max(max, onesCount);
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}), 3);
        Assert.assertEquals(findMaxConsecutiveOnes2(new int[]{1, 1, 0, 1, 1, 1}), 3);
    }
}
