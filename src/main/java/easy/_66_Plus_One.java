package easy;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/21/19.
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 */
public class _66_Plus_One {

    // core logic: start iterating from last digit and add carry (1) to the digit. if the result is <=9, return, else set the digit to 0 and continue the loop
    // if you reached end of the loop, it means all the digits in the input are 9's. so create the new array (with size n+1) and set the most significant digit to 1
    private static int[] plusOne(int[] digits) {
        int carry = 1;
        int n = digits.length - 1;
        for (int i = n; i >= 0; i--) {
            digits[i] += carry;
            if (digits[i] <= 9) {
                return digits;  // early return: no need to process further
            } else {
                digits[i] = 0;    // as digits[i] in the input is 9, and 9+1 = 10, this digit should be 0 in the output
            }
        }
        int[] res = new int[digits.length + 1];   // this means all the digits in the input are 9's, so you need one more extra digit in the output
        res[0] = 1;  // set the main significant digit to 1: eg: 999 + 1 = 1000
        return res;
    }

    public static void main(String[] args) {
        assertEquals(plusOne(new int[]{1, 2, 3}), new int[]{1, 2, 4});
        assertEquals(plusOne(new int[]{9, 9, 9}), new int[]{1, 0, 0, 0});
    }
}