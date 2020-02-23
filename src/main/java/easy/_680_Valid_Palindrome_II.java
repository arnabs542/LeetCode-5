package easy;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by udaythota on 8/17/19.
 * <p>
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * </p>
 */
public class _680_Valid_Palindrome_II {
    // core logic: when you encounter a non matching start and end character, check if (start+1, end) or (start, end-1) is a palindrome (excluding the one wrong character). if its palindrome return true, else false
    // TC: O(n): since each character is visited only once except for characters left and right to the wrong character
    private static boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isSubStringPalindrome(s, i + 1, j) || isSubStringPalindrome(s, i, j - 1);  // check if one of the sub strings is a valid palindrome
            }
            i++;
            j--;
        }
        return true;
    }

    // helper function to check if the given sub string is a valid palindrome
    private static boolean isSubStringPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        assertTrue(validPalindrome("aba"));
        assertTrue(validPalindrome("abca"));
        assertFalse(validPalindrome("abc"));
    }
}