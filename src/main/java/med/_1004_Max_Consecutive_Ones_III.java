package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 * </p>
 */
public class _1004_Max_Consecutive_Ones_III {
    // core logic: sliding window solution: move the end pointer while keeping a count of zeroes, and whenever they exceed K in the given sliding window, move the start pointer to adjust the zeroes to K. keep moving the end pointer and repeat the process.
    // in any sliding window the utmost zeroes we can have is K: this drives the solution
    // better understood when put on paper
    // TC: O(n)
    private static int longestOnes(int[] A, int K) {
        int start = 0, zeroesCount = 0, max = 0;
        for (int end = 0; end < A.length; end++) {
            if (A[end] == 0) {
                zeroesCount++;
            }
            while (zeroesCount > K) {
                if (A[start] == 0) {
                    zeroesCount--;
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2), 6);
        Assert.assertEquals(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3), 10);
        Assert.assertEquals(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 2), 6);
    }
}
