package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by z002jsf on 2/5/20.
 * <p>
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 * </p>
 */
public class _442_Find_All_Duplicates_Array {
    // core logic: for each and every element, flip the number corresponding to that index to its negative value. when you encounter a negative element the next time you want to flip, it means this is a duplicate number in the array
    // it relies on the fact that the elements in the array lies only in the range of: 1 ≤ a[i] ≤ n
    // TC: O(n), SC: O(1)
    private static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(index + 1);
            } else {
                nums[index] = -nums[index];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}), Arrays.asList(2, 3));
    }
}