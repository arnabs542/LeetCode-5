package easy;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/2/19.
 * <p>
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * </p>
 */
public class _136_SingleNumber {

    // core logic: bitwise XOR: a) 0 ^ N = N    b) N ^ N = 0
    // as each number is repeated twice, the final number left over would be single number
    private static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    // core logic: simple math
    private static int singleNumber2(int[] nums) {
        return 2 * Arrays.stream(nums).distinct().sum() - Arrays.stream(nums).sum();
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(singleNumber(new int[]{2, 2, 1}), 1);
        assertEquals(singleNumber(new int[]{4, 1, 2, 1, 2}), 4);
        assertEquals(singleNumber(new int[]{5, 6, 7, 5, 6}), 7);

        // test method: 2
        assertEquals(singleNumber2(new int[]{2, 2, 1}), 1);
        assertEquals(singleNumber2(new int[]{4, 1, 2, 1, 2}), 4);
        assertEquals(singleNumber2(new int[]{5, 6, 7, 5, 6}), 7);
    }
}