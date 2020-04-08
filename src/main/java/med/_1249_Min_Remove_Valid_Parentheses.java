package med;

import org.testng.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by udaythota on 4/7/20.
 * <p>
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * </p>
 */
public class _1249_Min_Remove_Valid_Parentheses {
    // core logic: 2 pass solution: 1st pass (from left to right) is to mark the invalid ')' from the string and 2nd pass (from right to left) is to mark the invalid '('. finally add the valid char positions to the result
    // TC: O(n), SC: O(n)
    private static String minRemoveToMakeValid(String input) {
        if (input == null || input.equals("")) {
            return input;
        }

        int open = 0, close = 0;
        boolean[] invalid = new boolean[input.length()];
        StringBuilder result = new StringBuilder();

        // 1st pass (from left to right) -> to mark the invalid ')'
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                open++;
            } else if (input.charAt(i) == ')') {
                if (open > 0) {
                    open--;
                } else {
                    invalid[i] = true;
                }
            }
        }
        // 2nd pass (from right to left) -> to mark the invalid '('.
        for (int j = input.length() - 1; j >= 0; j--) {
            if (input.charAt(j) == ')') {
                close++;
            } else if (input.charAt(j) == '(') {
                if (close > 0) {
                    close--;
                } else {
                    invalid[j] = true;
                }
            }
        }
        // append all the valid char positions to the result
        for (int i = 0; i < input.length(); i++) {
            if (!invalid[i]) {
                result.append(input.charAt(i));
            }
        }
        return result.toString();
    }

    // approach 2: using stack to keep track of invalid positions
    private static String minRemoveToMakeValid2(String input) {
        if (input == null || input.equals("")) {
            return input;
        }
        Stack<Integer> stack = new Stack<>();  // contains all the invalid positions
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else if (input.charAt(i) == ')') {
                if (!stack.isEmpty() && input.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }

        Set<Integer> set = new HashSet<>(stack);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (!set.contains(i)) {
                result.append(input.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // solution: 1
        Assert.assertEquals(minRemoveToMakeValid("lee(t(c)o)de)"), "lee(t(c)o)de");
        Assert.assertEquals(minRemoveToMakeValid("a)b(c)d"), "ab(c)d");
        Assert.assertEquals(minRemoveToMakeValid("))(("), "");
        Assert.assertEquals(minRemoveToMakeValid("(a(b(c)d)"), "a(b(c)d)");

        // solution: 2
        Assert.assertEquals(minRemoveToMakeValid2("lee(t(c)o)de)"), "lee(t(c)o)de");
        Assert.assertEquals(minRemoveToMakeValid2("a)b(c)d"), "ab(c)d");
        Assert.assertEquals(minRemoveToMakeValid2("))(("), "");
        Assert.assertEquals(minRemoveToMakeValid2("(a(b(c)d)"), "a(b(c)d)");
    }
}
