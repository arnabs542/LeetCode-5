package _5_Longest_Palindromic_Substring;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 7/12/19.
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * </p>
 */
public class _227_Basic_Calculator_II {

    // core logic: whenever you encounter a number (each number = digits from current index till you encounter an operation), calculate the result for previous number and keep continuing the loop
    // the trick part is how to handle the '-' operation [3 - 2 = 3 + (-2)]
    private static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        int prevNum = 0;
        int sum = 0;
        char prevOp = '+';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (s.charAt(i) == ' ') {  // skip the empty spaces
                continue;
            }

            if (Character.isDigit(ch)) {
                int val = ch - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {   // calculate the number using its place values
                    val = val * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (prevOp == '+') {
                    sum += prevNum;
                    prevNum = val;
                } else if (prevOp == '-') {
                    sum += prevNum;
                    prevNum = -val;     // [3 - 2 = 3 + (-2)]
                } else if (prevOp == '*') {
                    prevNum = prevNum * val;
                } else {
                    prevNum = prevNum / val;
                }
            } else {
                prevOp = s.charAt(i);
            }
        }
        sum += prevNum;   // calculate the sum for the last number
        return sum;
    }

    public static void main(String[] args) {
        assertEquals(calculate("3-2*2"), -1);
        assertEquals(calculate(" 3/2 "), 1);
        assertEquals(calculate(" 3+5 / 2 "), 5);
    }
}
