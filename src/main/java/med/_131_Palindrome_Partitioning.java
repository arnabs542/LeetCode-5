package med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by udaythota on 2/23/20.
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * </p>
 */
public class _131_Palindrome_Partitioning {
    // classic backtrack: check for all the possible sub strings in s if they are palindromes and add the sub strings to the result accordingly
    // TODO: TC: O(n^2) ??
    private static List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        List<List<String>> result = new ArrayList<>();
        dfsHelper(s, result, new ArrayList<>(), 0);
        return result;
    }

    private static void dfsHelper(String s, List<List<String>> result, List<String> partialList, int index) {
        if (index == s.length()) {
            result.add(new ArrayList<>(partialList));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                String subPalindrome = s.substring(index, i + 1);
                partialList.add(subPalindrome);   // add the palindrome to the result
                dfsHelper(s, result, partialList, i + 1); // explore the right of this string to check if it is palindrome
                partialList.remove(partialList.size() - 1);   // DFS done searching: remove the current elements from partial list so we can check for new paths
            }
        }
    }

    // palindrome helper function
    private static boolean isPalindrome(String input, int start, int end) {
        while (start < end) {
            if (input.charAt(start++) != input.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assertEquals(partition("aab"), Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b")));
    }
}