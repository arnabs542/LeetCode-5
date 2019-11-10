package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 11/10/19.
 * <p>
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * </p>
 */
public class _448_Find_All_Numbers_Disappeared_Array {
    // core logic: the input constraint says that the input values are only from 1 to n. this means all the values will be in between 1 and n, which means we can use the index (0 to n-1) to keep track of visited elements (mark the elements as negative) in the first pass
    // in the second pass, we can use this index to see if the element is already visited (already negative). if the index element is positive, it means this has not been visited and should be added to the result
    // NOTE: we should add i + 1, as we use the indexes to track, but the output we need is the actual element and not the index
    // TC: O(n) SC: O(1)
    private static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;  // this is to always get the positive index (for the duplicated elements, the visited index will be negative, so we need to make sure we only take the positive one)
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);  // add i + 1, as we need the actual element and not the index
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}), Arrays.asList(5, 6));
    }
}