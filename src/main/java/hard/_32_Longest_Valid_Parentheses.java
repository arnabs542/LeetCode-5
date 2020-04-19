package hard;

import org.testng.Assert;

import java.util.Stack;

/**
 * Created by udaythota on 4/18/20.
 * <p>
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * </p>
 */
public class _32_Longest_Valid_Parentheses {
    // similar to below explanation
    // TC: O(n), SC: O(n)
    private static int longestValidParentheses(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int longest = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ')' && stack.size() > 1 && input.charAt(stack.peek()) == '(') {   // the last 2 conditions are to handle the corner cases (eg: string starting with ')')
                stack.pop();
                longest = Math.max(longest, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return longest;
    }

    // though more if and else conditions, this is little more intuitive than the above solution
    // core logic: whenever we find a valid pair, () -> calculate the length between the current position and prev invalid position. update this length when we encounter a length greater than the current length
    // whenever we see a '(' push to the stack, and when we encounter ')', if the stack is valid, pop from the stack
    private static int longestValidParentheses2(String input) {
        Stack<Integer> stack = new Stack<>();
        int left = -1;
        int longest = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {   // to handle the case where the string starts with ')'   eg: input: )))()  -> in this case left = 2
                    left = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        longest = Math.max(longest, i - left);   // as stack is empty, calculate the length using left
                    } else {
                        longest = Math.max(longest, i - stack.peek());   // as stack is not empty, calculate the length using last invalid
                    }
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        Assert.assertEquals(longestValidParentheses("(()"), 2);
        Assert.assertEquals(longestValidParentheses(")()())"), 4);
        Assert.assertEquals(longestValidParentheses("()()())"), 6);

        Assert.assertEquals(longestValidParentheses2("(()"), 2);
        Assert.assertEquals(longestValidParentheses2(")()())"), 4);
        Assert.assertEquals(longestValidParentheses2("()()())"), 6);
        Assert.assertEquals(longestValidParentheses2(")))(())"), 4);
    }
}
