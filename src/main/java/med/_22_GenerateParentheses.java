package med;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udaythota on 6/23/19.
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * </p>
 */
public class _22_GenerateParentheses {

    // core logic: perform DFS and whenever you encounter the desired string, add it to the result.
    // the below 3 steps needs to be taken care when performing DFS:
    // a) the number of open parentheses <= number of closing parentheses
    // b) the max number of open parentheses <= n
    // c) when open ==n && close ==n, add the string to the result
    // below is the result path when performing DFS
    /*
    (
    ((
    (((
    ((()
    ((())
    ((()))
    (()
    (()(
    (()()
    (()())
    (())
    (())(
    (())()
    ()
    ()(
    ()((
    ()(()
    ()(())
    ()()
    ()()(
    ()()()
     */

    private static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helper("", 0, 0, n, result);
        return result;
    }

    private static void helper(String string, int open, int close, int n, List<String> result) {
        if (string.length() == 2 * n) {    // n close and n open (this condition can also be replaced with open == n && close == n)
            result.add(string);
            return;
        }

        if (open < n) {
            helper(string + "(", open + 1, close, n, result);
        }
        if (close < open) {
            helper(string + ")", open, close + 1, n, result);
        }
    }

    // same as above approach except that, instead of using string concatenations which are expensive (each string + "" creates a new string internally), we use string builder here
    // NOTE: when using simple String, you pass str+"(" or str+")" to the next function by keeping the str the ORIGINAL value.
    // However, when using the StringBuilder, you plus "(" or ")" to the str first, then you pass the str to the next function. In this case, the str becomes a new value. Thus, you have to delete them after the function called so that when it comes to the next "if" statement, the str will stay in the original value.
    private static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        helper2(new StringBuilder(), 0, 0, n, result);
        return result;
    }

    private static void helper2(StringBuilder sb, int open, int close, int n, List<String> result) {
        if (open == n && close == n) {   // equivalent to sb.length() == 2 * n
            result.add(sb.toString());
            return;
        }

        if (open < n) {
            helper2(sb.append("("), open + 1, close, n, result);
            sb.setLength(sb.length() - 1);   // see the note above
        }
        if (close < open) {
            helper2(sb.append(")"), open, close + 1, n, result);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        // test method: 1
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(5));

        // test method: 2
        System.out.println(generateParenthesis2(3));
        System.out.println(generateParenthesis2(5));
    }
}