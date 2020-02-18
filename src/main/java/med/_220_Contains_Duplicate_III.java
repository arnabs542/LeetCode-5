package med;

import java.util.TreeSet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 2/17/20.
 * <p>
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * </p>
 */
public class _220_Contains_Duplicate_III {
    // core logic: sliding window + tree set. maintain the tree set in such a way that it always contains K elements and slide them accordingly (remove 1st element when we reach size >=k, etc..)
    // the fact that the absolute diff should always be utmost t means that we need to check the greatest low and lowest high for all the elements. if that satisfies the given condition, return true. eg: if t = 2 and nums[i] = 4, for the condition to be true the greatest element smaller than 4 should be in between 2 and 4, while smallest element greater than 4 should lie between 4 and 6.
    // TC: O(NlogK) - as the size of tree set is always k and n is the size of the array
    private static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer low = treeSet.floor(nums[i]);   // get the greatest element smaller than this number
            Integer high = treeSet.ceiling(nums[i]);  // get the smallest element greater than this number

            if ((low != null && (long) nums[i] - low <= t) || (high != null && (long) high - nums[i] <= t)) {   // check for absolute differences
                return true;
            }
            treeSet.add(nums[i]);

            if (i >= k) {   // remove the first element when size of the tree set exceeds K
                treeSet.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        assertTrue(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        assertFalse(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}