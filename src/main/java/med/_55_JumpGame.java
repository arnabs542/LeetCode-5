package med;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 1/19/20.
 * <p>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * </p>
 */
public class _55_JumpGame {
    // this is a greedy and optimal solution. the same problem can also be solved through standard DP approaches
    // core logic: starting from the last position, we identify if from the given position we can move to the next position, which means that the current position (or the index) is a good position.
    // so update the good position index. repeat this till the zero'th index and if we reach zero'th index return true
    private static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int lastGoodIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastGoodIndex) {
                lastGoodIndex = i;
            }
        }
        return lastGoodIndex == 0;  // if we reached to the 0th index, that means its a good index and there is a possible jump to the end position
    }

    // TODO: working, but think on it later
    private static boolean canJump2(int[] nums) {
        if (nums.length < 2) {
            return true;
        }

        int max = nums[0];
        int current = 0;
        while (current <= max && max < nums.length - 1) {
            max = Math.max(max, nums[current] + current);
            current++;
        }
        return max >= nums.length - 1;
    }

    public static void main(String[] args) {
        assertTrue(canJump(new int[]{2, 3, 1, 1, 4}));
        assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));

        assertTrue(canJump2(new int[]{2, 3, 1, 1, 4}));
        assertFalse(canJump2(new int[]{3, 2, 1, 0, 4}));
    }
}