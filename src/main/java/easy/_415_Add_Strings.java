package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/29/19.
 * <p>
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 * </p>
 */
public class _415_Add_Strings {
    // core logic: process from right to left and keep a note of carry in each and every step. in the end, reverse and return the string
    // TC: O(Max(l1, l2)) - where l1 and l2 are the lengths of strings n1 and n2.
    // SC: O(l1+l2) - space required for string builder
    private static String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while (l1 >= 0 || l2 >= 0) {
            int digit1 = 0, digit2 = 0;   // these should reset to zeroes at end of each iteration
            if (l1 >= 0) {
                digit1 = num1.charAt(l1--) - '0';
            }

            if (l2 >= 0) {
                digit2 = num2.charAt(l2--) - '0';
            }
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            stringBuilder.append(sum % 10);
        }

        if (carry > 0) {   // add the carry if any remaining (if the first 2 digits has a left over carry)
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();   // reverse the result as we processed from right to left
    }

    public static void main(String[] args) {
        assertEquals(addStrings("20", "23"), "43");
        assertEquals(addStrings("200", "23"), "223");
        assertEquals(addStrings("4223", "30"), "4253");
    }
}