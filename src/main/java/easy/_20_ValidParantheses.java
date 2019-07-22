package easy;

import java.util.HashMap;
import java.util.Stack;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by udaythota on 6/23/19.
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * </p>
 */
public class _20_ValidParantheses {

    // core logic: keep a map of opening and its corresponding closing character. iterate through the string, push opening chars to stack and when a closing char occurs, see if the last element pushed to stack is same. if no return false, else continue
    // TC: O(n)
    private static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();

        for (Character ch : s.toCharArray()) {
            if (map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || !map.get(stack.pop()).equals(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();  // stack should be empty for all valid strings
    }

    // optimized version to the above approach: don't need a map to store the values
    // core logic: if you encounter an opening char, push its corresponding closing char to the stack, else pop the stack and see its equal. if no, return false, else continue
    private static boolean isValid2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 != 0) {  // strings with odd number of characters are never valid
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (Character ch : s.toCharArray()) {
            if (ch.equals('(')) {
                stack.push(')');
            } else if (ch.equals('[')) {
                stack.push(']');
            } else if (ch.equals('{')) {
                stack.push('}');
            } else {
                if (stack.isEmpty() || !stack.pop().equals(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();  // stack should be empty for all valid strings
    }

    public static void main(String[] args) {

        // test method: 1
        assertTrue(isValid("()"));
        assertTrue(isValid("()[]{}"));
        assertFalse(isValid("(]"));
        assertFalse(isValid("([)]"));
        assertTrue(isValid("{[]}"));
        assertFalse(isValid("]"));

        // test method: 2
        assertTrue(isValid2("()"));
        assertTrue(isValid2("()[]{}"));
        assertFalse(isValid2("(]"));
        assertFalse(isValid2("([)]"));
        assertTrue(isValid2("{[]}"));
        assertFalse(isValid2("]"));
        assertFalse(isValid2("(("));
    }
}
