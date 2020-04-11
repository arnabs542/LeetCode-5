package med;

import org.testng.Assert;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 * </p>
 */
public class _1248_Count_Nice_Sub_Arrays {
    // core logic: sliding window solution. because all our previous sliding window solution templates are to find utmost K numbers / utmost K swaps / utmost K flips / utmost K ones etc, and this question asks for exactly K integers, we can still use the same template to solve this
    // meat of the problem lies in deducting the fact that: exactly K times = utmost K times - utmost K-1 times
    // TC: O(n)
    private static int numberOfSubarrays(int[] nums, int k) {
        return utmostKHelper2(nums, k) - utmostKHelper2(nums, k - 1);
    }

    // core logic: sliding window solution: this is exactly same as utmost k numbers in an array
    // same logic as LC: 1004, 485, 487, etc
    private static int utmostKHelper(int[] nums, int k) {
        int start = 0, count = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] % 2 != 0) {   // when number is odd, decrement k (moving the right window)
                k--;
            }
            while (k < 0) {
                if (nums[start] % 2 != 0) {  // when number is odd, increment k (moving the left window)
                    k++;
                }
                start++;
            }
            count += end - start + 1;
        }
        return count;
    }

    // just a diff way of writing the above helper function. uses the oddNumbers variable to track the number of odd numbers encountered till that point
    // slightly more intuitive than the above helper method
    private static int utmostKHelper2(int[] nums, int k) {
        int start = 0, count = 0, oddNumbers = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] % 2 != 0) {   // when number is odd, decrement k (moving the right window)
                oddNumbers++;
            }
            while (oddNumbers > k) {
                if (nums[start] % 2 != 0) {  // when number is odd, increment k (moving the left window)
                    oddNumbers--;
                }
                start++;
            }
            count += end - start + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Assert.assertEquals(numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3), 2);
        Assert.assertEquals(numberOfSubarrays(new int[]{2, 4, 6}, 1), 0);
        Assert.assertEquals(numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2), 16);
    }
}
