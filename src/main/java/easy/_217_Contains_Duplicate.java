package easy;

import java.util.Arrays;
import java.util.HashSet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 7/12/19.
 * <p>
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * </p>
 */
public class _217_Contains_Duplicate {
    private static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return Arrays.stream(nums).distinct().count() != nums.length;
    }

    public static void main(String[] args) {
        // test method: 1
        assertFalse(containsDuplicate(new int[]{1, 2, 3, 4}));
        assertTrue(containsDuplicate(new int[]{1, 2, 3, 1}));
        assertTrue(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));

        // test method: 2
        assertFalse(containsDuplicate2(new int[]{1, 2, 3, 4}));
        assertTrue(containsDuplicate2(new int[]{1, 2, 3, 1}));
        assertTrue(containsDuplicate2(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }
}