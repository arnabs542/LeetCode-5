package hard;

import org.testng.Assert;

import java.util.*;

/**
 * Created by udaythota on 4/11/20.
 * <p>
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * </p>
 */
public class _301_Remove_Invalid_Parentheses {
    // BFS solution: add input string to the queue and keep exploring all the levels (first level indicates removing all the possible single chars and adding them to queue. in the second level, for every string in the queue, explore further: remove one more parentheses from all the strings and add them to the queue and keep processing..)
    // as we are doing BFS, it is guaranteed that the result strings we get are the strings with minimum valid parentheses removed
    // TC: O(n * 2^n) -> as for every string we explore all the possible candidate strings at that level
    // can also be done using DFS. pursue it sometime later
    // see here for more explanation: https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
    private static List<String> removeInvalidParentheses(String input) {
        List<String> result = new ArrayList<>();
        if (input == null) {
            return result;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(input);
        visited.add(input);
        boolean found = false;

        while (!queue.isEmpty()) {
            String string = queue.poll();
            if (isValid(string)) {
                result.add(string);
                found = true;
            }

            if (found) {   // IMPORTANT: when valid string is found at some level, we don't need to further explore the levels in BFS, but the queue could already have possible candidates (valid parentheses strings) at the same level, so process the elements which are already in the queue
                continue;
            }
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) != '(' && string.charAt(i) != ')') {   // any other chars other than open and close parentheses, don't do anything
                    continue;
                }

                String candidateString = string.substring(0, i) + string.substring(i + 1, string.length());   // remove the char at ith position to form the next candidate string
                if (!visited.contains(candidateString)) {   // don't process the duplicate strings: so check in the visited set before adding to the queue
                    queue.add(candidateString);
                    visited.add(candidateString);
                }
            }
        }
        return result;
    }

    // helper method to check if the parentheses in the given string is valid or not
    private static boolean isValid(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                count++;
            }
            if (input.charAt(i) == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        Assert.assertEquals(removeInvalidParentheses("()())()"), Arrays.asList("(())()", "()()()"));
        Assert.assertEquals(removeInvalidParentheses("(a)())()"), Arrays.asList("(a())()", "(a)()()"));
        Assert.assertEquals(removeInvalidParentheses(")("), Arrays.asList(""));
    }
}
