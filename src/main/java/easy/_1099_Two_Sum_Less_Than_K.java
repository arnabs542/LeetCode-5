package easy;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.
 * </p>
 */
public class _1099_Two_Sum_Less_Than_K {
    // core logic: sort + 2 pointer solution. navigate 2 pointers to bring them closer to k. when the sum is less than k, update the result so we have the floor sum (max sum less than k) by end of the loop
    // TC: O(nlogn) - sorting cost
    private static int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int floorSum = -1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < k) {
                floorSum = Math.max(floorSum, sum);
                i++;
            } else {
                j--;
            }
        }
        return floorSum;
    }

    public static void main(String[] args) {
        Assert.assertEquals(twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60), 58);
        Assert.assertEquals(twoSumLessThanK(new int[]{10, 20, 30}, 15), -1);
    }
}
