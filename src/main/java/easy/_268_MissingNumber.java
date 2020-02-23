package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/13/19.
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * </p>
 */
public class _268_MissingNumber {
    // core logic: XOR is its own inverse (meaning when you XOR same elements, the result would be 1). so when you XOR all the elements in the array with 0 to nums.length -1, the missing number would be the final result
    private static int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int missing = nums.length;   // as nums.length is also part of input array, assume it as missing (when its xor with the corresponding index, it would be 1)
        for (int i = 0; i < nums.length; i++) {
            missing ^= nums[i] ^ i;
        }
        return missing;
    }

    // core logic: the sum of n numbers from 0 to n should be (n * n+1) / 2
    private static int missingNumber2(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(missingNumber(new int[]{3, 0, 1}), 2);
        assertEquals(missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}), 8);

        // test method: 2
        assertEquals(missingNumber2(new int[]{3, 0, 1}), 2);
        assertEquals(missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}), 8);
    }
}