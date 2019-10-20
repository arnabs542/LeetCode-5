package _5_Longest_Palindromic_Substring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/28/19.
 * <p>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * </p>
 */
public class _229_Majority_Element_2 {
    // core logic: boyer-moore voting algorithm: assume candidate1 and candidate2 to be the majority 2 elements and for all the new elements encountered, initiate the element count to 1, if repeated again, increment the counts, else decrement the counts
    // 2nd pass is to make sure the candidates we got by end of the loop are really the majority elements (as the majority elements might NOT altogether exist in the input)
    private static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // this 2nd loop is to make sure the candidates we got are really the majority elements (as the input is NOT guaranteed to have the majority elements ALL the time)
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }
        return result;
    }

    public static void main(String[] args) {
        assertEquals(majorityElement(new int[]{3, 2, 3}), Arrays.asList(3));
        assertEquals(majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}), Arrays.asList(1, 2));
    }
}