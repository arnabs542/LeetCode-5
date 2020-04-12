package easy;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 * </p>
 */
public class _674_Longest_Cont_Inc_Sub_Seq {
    // core logic: increase the currentMax when curr number is greater than prev number, if not reset the count. keep updating the max as needed
    private static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currentMax = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentMax++;
                max = Math.max(max, currentMax);
            } else {
                currentMax = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(findLengthOfLCIS(new int[]{1, 3, 5, 4, 7}), 3);
        Assert.assertEquals(findLengthOfLCIS(new int[]{2, 2, 2, 2, 2}), 1);
    }
}
