package med;

import java.util.Stack;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 10/16/19.
 * <p>
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * </p>
 */
public class _402_Remove_K_Digits {
    private static String removeKdigits(String num, int k) {
        if (num == null || num.length() <= k) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !stack.isEmpty() && num.charAt(i) < stack.peek()) {   // whenever we meet a digit which is less than the previous digit, discard the previous one
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }

        // corner case like "1111"
        while (k > 0) {
            stack.pop();
            k--;
        }

        while (!stack.isEmpty()) {   // reverse the string
            stringBuilder.insert(0, stack.pop());
        }

        while (stringBuilder.length() > 1 && stringBuilder.charAt(0) == '0') {  // delete the first chars if '0'
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        assertEquals(removeKdigits("1432219", 3), "1219");
        assertEquals(removeKdigits("10200", 1), "200");
        assertEquals(removeKdigits("10", 2), "0");
        assertEquals(removeKdigits("1234567890", 9), "0");
    }
}