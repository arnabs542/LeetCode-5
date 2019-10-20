package med;

import java.util.Arrays;
import java.util.Comparator;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/7/19.
 * <p>
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * </p>
 */
public class _179_LargestNumber {

    // core logic: use the string comparator to place the numbers in the right order
    // TC: O(NlogN)
    private static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        String[] stringArray = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            stringArray[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(stringArray, (a, b) -> (b + a).compareTo(a + b));   // custom comparator which compares based on the string concatenated value (b+a should be compared with a+b as we can just append the strings later)
        return stringArray[0].equals("0") ? "0" : String.join("", stringArray);  // corner case to handle when there are all zeroes in the input (the output should be '0' instead of '00..')
    }

    // same as above logic, just a different style of writing a comparator (without using lambdas)
    private static String largestNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        String[] stringArray = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            stringArray[i] = String.valueOf(nums[i]);
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String string1, String string2) {
                String s1 = string1 + string2;
                String s2 = string2 + string1;
                return s2.compareTo(s1);   // NOTE: compare string2 with string1 so we can just append later
            }
        };

        Arrays.sort(stringArray, comparator);
        return stringArray[0].equals("0") ? "0" : String.join("", stringArray);
    }

    public static void main(String[] args) {
        // test method: 1
        assertEquals(largestNumber(new int[]{10, 2}), "210");
        assertEquals(largestNumber(new int[]{3, 30, 34, 5, 9}), "9534330");
        assertEquals(largestNumber(new int[]{0, 0}), "0");

        // test method: 2
        assertEquals(largestNumber2(new int[]{10, 2}), "210");
        assertEquals(largestNumber2(new int[]{3, 30, 34, 5, 9}), "9534330");
        assertEquals(largestNumber2(new int[]{0, 0}), "0");
    }
}
