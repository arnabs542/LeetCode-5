package _5_Longest_Palindromic_Substring;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/3/19.
 * <p>
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * </p>
 */
public class _43_Multiply_Strings {
    // core logic: while multiplying each number, place them at their appropriate positions
    // reference: https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
    private static String multiply(String num1, String num2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return stringBuilder.toString();
        }
        int len1 = num1.length();
        int len2 = num2.length();

        int[] result = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + result[p2];

                result[p1] += sum / 10;
                result[p2] = sum % 10;
            }
        }
        for (int p : result) {
            if (!(stringBuilder.length() == 0 && p == 0)) {   // first digit if '0' shouldn't be added to the result
                stringBuilder.append(p);
            }
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(multiply("123", "456"), "56088");
        assertEquals(multiply("3", "2"), "6");
    }
}