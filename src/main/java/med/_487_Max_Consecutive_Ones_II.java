package med;

import org.testng.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * </p>
 */
public class _487_Max_Consecutive_Ones_II {
    // sliding window solution: exact same as LC: 1004. just replace K with 1 here
    private static int findMaxConsecutiveOnes(int[] nums) {
        int start = 0, zeroesCount = 0, max = 0, k = 1;   // in this case k = 1: can be generalized to any number
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                zeroesCount++;
            }
            while (zeroesCount > k) {   // NOTE: while / if both are equivalent here
                if (nums[start] == 0) {
                    zeroesCount--;
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    // alternate solution: to handle the follow up. queue maintains the state of zero indices occurred. when queue size is more than k, poll from queue till queue size is K
    // the queue size should be utmost K in all the sliding windows. when it increases, adjust it accordingly
    // TC: O(n), SC: O(n)
    private static int findMaxConsecutiveOnes2(int[] nums) {
        int max = 0, start = 0, k = 1;
        Queue<Integer> queue = new LinkedList<>();
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                queue.offer(end);
            }
            while (queue.size() > k) {
                start = queue.poll() + 1;
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}), 4);
        Assert.assertEquals(findMaxConsecutiveOnes2(new int[]{1, 0, 1, 1, 0}), 4);
    }
}
