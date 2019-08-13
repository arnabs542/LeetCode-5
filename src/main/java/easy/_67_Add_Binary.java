package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 8/11/19.
 * <p>
 * Given two binary strings, return their sum (also a binary string).
 * </p>
 */
public class _67_Add_Binary {
    // core logic: add from right to left in both the strings and reverse the final output string (as the result is computed and added from right to left)
    private static String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int sum;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            sum = carry;    // assign the previous carry to current sum
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            stringBuilder.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            stringBuilder.append(carry);
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        assertEquals(addBinary("11", "1"), "100");
        assertEquals(addBinary("1010", "1011"), "10101");
    }
}